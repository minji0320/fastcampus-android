package fastcampus.aop.part5.chapter05.data.db

import androidx.room.*
import fastcampus.aop.part5.chapter05.data.db.entity.StationEntity
import fastcampus.aop.part5.chapter05.data.db.entity.StationSubwayCrossRefEntity
import fastcampus.aop.part5.chapter05.data.db.entity.StationWithSubwaysEntity
import fastcampus.aop.part5.chapter05.data.db.entity.SubwayEntity
import fastcampus.aop.part5.chapter05.domain.Station
import kotlinx.coroutines.flow.Flow

@Dao
interface StationDao {

    // Query는 Transaction을 붙여야하지만, Insert 등은 내부에서 Transaction 처리 수행
    // Coroutines Flow -> Observable (LiveData도 가능)
    @Transaction
    @Query("SELECT * FROM StationEntity")
    fun getStationWithSubways(): Flow<List<StationWithSubwaysEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(station: List<StationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubways(subways: List<SubwayEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossReferences(reference: List<StationSubwayCrossRefEntity>)

    @Transaction
    suspend fun insertStationSubways(stationSubways: List<Pair<StationEntity, SubwayEntity>>) {
        insertStations(stationSubways.map { it.first })
        insertSubways(stationSubways.map { it.second })
        insertCrossReferences(
            stationSubways.map { (station, subway) ->
                StationSubwayCrossRefEntity(
                    station.stationName,
                    subway.subwayId
                )
            }
        )
    }

    @Update
    suspend fun updateStation(station: StationEntity)
}