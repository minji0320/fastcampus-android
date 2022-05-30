package fastcampus.aop.part6.chapter01.screen.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import fastcampus.aop.part6.chapter01.R
import fastcampus.aop.part6.chapter01.data.repository.order.DefaultOrderRepository
import fastcampus.aop.part6.chapter01.data.repository.order.OrderRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.part6.chapter01.model.CellType
import fastcampus.aop.part6.chapter01.model.restaurant.food.FoodModel
import fastcampus.aop.part6.chapter01.screen.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OrderMenuListViewModel(
    private val restaurantFoodRepository: RestaurantFoodRepository,
    private val orderRepository: OrderRepository,
) : BaseViewModel() {

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }

    val orderMenuStateLiveData =
        MutableLiveData<OrderMenuState>(OrderMenuState.Uninitialized)

    override fun fetchData(): Job = viewModelScope.launch {
        orderMenuStateLiveData.value = OrderMenuState.Loading
        val foodMenuList = restaurantFoodRepository.getAllFoodMenuListInBasket()
        orderMenuStateLiveData.value = OrderMenuState.Success(
            foodMenuList.map {
                FoodModel(
                    id = it.hashCode().toLong(),
                    type = CellType.ORDER_FOOD_CELL,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageUrl = it.imageUrl,
                    restaurantId = it.restaurantId,
                    foodId = it.id
                )
            }
        )
    }

    fun orderMenu() = viewModelScope.launch {
        val foodMenuList = restaurantFoodRepository.getAllFoodMenuListInBasket()
        if (foodMenuList.isNotEmpty()) {
            val restaurantId = foodMenuList.first().restaurantId
            firebaseAuth.currentUser?.let { user ->
                when (val data = orderRepository.orderMenu(user.uid, restaurantId, foodMenuList)) {
                    is DefaultOrderRepository.Result.Success<*> -> {
                        restaurantFoodRepository.clearFoodMenuListInBasket()
                        orderMenuStateLiveData.value = OrderMenuState.Order
                    }
                    is DefaultOrderRepository.Result.Error -> {
                        orderMenuStateLiveData.value = OrderMenuState.Error(
                            R.string.request_error, data.e
                        )
                    }
                }
            } ?: kotlin.run {
                orderMenuStateLiveData.value = OrderMenuState.Error(
                    R.string.request_error, IllegalAccessException()
                )
            }
        }
    }

    fun clearOrderMenu() = viewModelScope.launch {
        restaurantFoodRepository.clearFoodMenuListInBasket()
        fetchData()
    }

    fun removeOrderMenu(model: FoodModel) = viewModelScope.launch {
        restaurantFoodRepository.removeFoodMenuListInBasket(model.foodId)
        fetchData()
    }
}