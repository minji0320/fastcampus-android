package fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantDetailViewModel(
    private val restaurantEntity: RestaurantEntity,
) : BaseViewModel() {

    val restaurantDetailStateLiveData =
        MutableLiveData<RestaurantDetailState>(RestaurantDetailState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        restaurantDetailStateLiveData.value = RestaurantDetailState.Success(
            restaurantEntity = restaurantEntity
        )
    }

    fun getRestaurantTelNumber(): String? {
        return when (val data = restaurantDetailStateLiveData.value) {
            is RestaurantDetailState.Success -> {
                data.restaurantEntity.restaurantTelNumber
            }
            else -> null
        }
    }
}