package fastcampus.aop.part6.chapter01.data.repository.user

import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity

interface UserRepository {

    suspend fun getUserLocation(): LocationLatLngEntity?

    suspend fun insertUserLocation(locationLatLngEntity: LocationLatLngEntity)
}