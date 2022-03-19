package fastcampus.aop.part4.chapter03.model

data class SearchResultEntity(
    val fullAddress: String,
    val name: String,
    val locationLatLng: LocationLatLngEntity
)