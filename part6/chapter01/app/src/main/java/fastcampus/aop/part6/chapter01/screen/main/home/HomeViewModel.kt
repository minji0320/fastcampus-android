package fastcampus.aop.part6.chapter01.screen.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fastcampus.aop.part6.chapter01.R
import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.entity.MapSearchInfoEntity
import fastcampus.aop.part6.chapter01.data.repository.map.MapRepository
import fastcampus.aop.part6.chapter01.data.repository.user.UserRepository
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val mapRepository: MapRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    val homeStateLiveData = MutableLiveData<HomeState>(HomeState.Uninitialized)

    fun loadReverseGeoInformation(
        locationLatLngEntity: LocationLatLngEntity,
    ) = viewModelScope.launch {
        homeStateLiveData.value = HomeState.Loading
        val userLocation = userRepository.getUserLocation()
        val currentLocation = userLocation ?: locationLatLngEntity

        val addressInfo = mapRepository.getReverseGeoInformation(currentLocation)
        addressInfo?.let { info ->
            homeStateLiveData.value = HomeState.Success(
                mapSearchInfo = info.toSearchInfoEntity(locationLatLngEntity),
                isLocationSame = currentLocation == locationLatLngEntity
            )
        } ?: kotlin.run {
            homeStateLiveData.value = HomeState.Error(R.string.cannot_load_address_info)
        }
    }

    fun getMapSearchInfo(): MapSearchInfoEntity? {
        when (val data = homeStateLiveData.value) {
            is HomeState.Success -> {
                return data.mapSearchInfo
            }
        }
        return null
    }

    companion object {
        const val MY_LOCATION_KEY = "MyLocation"
    }

}