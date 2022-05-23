package fastcampus.aop.part6.chapter01.data.repository.user

import fastcampus.aop.part6.chapter01.data.db.dao.LocationDao
import fastcampus.aop.part6.chapter01.data.db.dao.RestaurantDao
import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultUserRepository(
    private val locationDao: LocationDao,
    private val restaurantDao: RestaurantDao,
    private val ioDispatcher: CoroutineDispatcher,
) : UserRepository {
    override suspend fun getUserLocation(): LocationLatLngEntity? = withContext(ioDispatcher) {
        locationDao.get(-1)
    }

    override suspend fun insertUserLocation(locationLatLngEntity: LocationLatLngEntity) =
        withContext(ioDispatcher) {
            locationDao.insert(locationLatLngEntity)
        }

    override suspend fun getUserLikedRestaurant(restaurantTitle: String): RestaurantEntity? =
        withContext(ioDispatcher) {
            restaurantDao.get(restaurantTitle)
        }

    override suspend fun insertUserLikedRestaurant(restaurantEntity: RestaurantEntity) =
        withContext(ioDispatcher) {
            restaurantDao.insert(restaurantEntity)
        }

    override suspend fun deleteUserLikedRestaurant(restaurantTitle: String) =
        withContext(ioDispatcher) {
            restaurantDao.delete(restaurantTitle)
        }

    override suspend fun deleteAllUserLikedRestaurant() =
        withContext(ioDispatcher) {
            restaurantDao.deleteAll()
        }
}