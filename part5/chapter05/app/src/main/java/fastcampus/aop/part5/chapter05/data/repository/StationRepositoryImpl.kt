package fastcampus.aop.part5.chapter05.data.repository

import fastcampus.aop.part5.chapter05.data.api.StationApi
import fastcampus.aop.part5.chapter05.data.api.StationArrivalsApi
import fastcampus.aop.part5.chapter05.data.api.response.mapper.toArrivalInformation
import fastcampus.aop.part5.chapter05.data.db.StationDao
import fastcampus.aop.part5.chapter05.data.db.entity.mapper.toStations
import fastcampus.aop.part5.chapter05.data.preference.PreferenceManager
import fastcampus.aop.part5.chapter05.domain.ArrivalInformation
import fastcampus.aop.part5.chapter05.domain.Station
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

// 매개변수는 appModule에서 주입받을 예정
class StationRepositoryImpl(
    private val stationArrivalsApi: StationArrivalsApi,
    private val stationApi: StationApi,
    private val stationDao: StationDao,
    private val preferenceManager: PreferenceManager,
    private val dispatcher: CoroutineDispatcher
) : StationRepository {

    override val stations: Flow<List<Station>> =
        stationDao.getStationWithSubways()
            .distinctUntilChanged() // 원하는 데이터의 변화만 파악하기 위해 선언 필요
            .map { it.toStations() }
            .flowOn(dispatcher) // 어떤 스레드에서 데이터가 흐를 것인가 -> IO

    // 파일 업데이트 시점과 db 업데이트 시점을 비교하여, db 업데이트 시점이 없거나 파일 업데이트 시점이 더 이후인 경우 db 업데이트 수행
    override suspend fun refreshStations() = withContext(dispatcher) {
        val fileUpdatedTimeMillis = stationApi.getStationDataUpdatedTimeMillis()
        val lastDatabaseUpdatedTimeMillis = preferenceManager.getLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS)

        if (lastDatabaseUpdatedTimeMillis == null || fileUpdatedTimeMillis > lastDatabaseUpdatedTimeMillis) {
            stationDao.insertStationSubways(stationApi.getStationSubways())
            preferenceManager.putLong(KEY_LAST_DATABASE_UPDATED_TIME_MILLIS, fileUpdatedTimeMillis)
        }
    }

    override suspend fun getStationArrivals(stationName: String): List<ArrivalInformation> = withContext(dispatcher) {
        stationArrivalsApi.getRealtimeStationArrivals(stationName)
            .body()
            ?.realtimeArrivalList
            ?.toArrivalInformation()
            ?.distinctBy { it.direction }
            ?.sortedBy { it.subway }
            ?: throw RuntimeException("도착 정보를 불러오는 데에 실패했습니다.")
    }


    companion object {
        private const val KEY_LAST_DATABASE_UPDATED_TIME_MILLIS = "KEY_LAST_DATABASE_UPDATED_TIME_MILLIS"
    }
    
}