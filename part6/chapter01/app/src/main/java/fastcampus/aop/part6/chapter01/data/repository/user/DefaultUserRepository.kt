package fastcampus.aop.part6.chapter01.data.repository.user

import fastcampus.aop.part6.chapter01.data.db.dao.LocationDao
import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultUserRepository(
    private val locationDao: LocationDao,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    override suspend fun getUserLocation(): LocationLatLngEntity? = withContext(ioDispatcher) {
        locationDao.get(-1)
    }

    override suspend fun insertUserLocation(locationLatLngEntity: LocationLatLngEntity) =
        withContext(ioDispatcher) {
            locationDao.insert(locationLatLngEntity)
        }
}