package fastcampus.aop.part6.chapter01.data.repository.map

import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.response.address.AddressInfo

interface MapRepository {

    suspend fun getReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity,
    ): AddressInfo?
}