package fastcampus.aop.part6.chapter01.screen.main.home.restaurant

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity
import fastcampus.aop.part6.chapter01.data.repository.RestaurantRepository
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RestaurantListViewModel(
    private val restaurantCategory: RestaurantCategory,
    private val restaurantRepository: RestaurantRepository,
) : BaseViewModel() {

    val restaurantListLiveData = MutableLiveData<List<RestaurantEntity>>()

    override fun fetchData(): Job = viewModelScope.launch {
        val restaurantList = restaurantRepository.getList(restaurantCategory)
        restaurantListLiveData.value = restaurantList
    }
}