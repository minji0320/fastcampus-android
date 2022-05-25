package fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail

import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity
import fastcampus.aop.part6.chapter01.data.entity.RestaurantFoodEntity

sealed class RestaurantDetailState {

    object Uninitialized : RestaurantDetailState()

    object Loading : RestaurantDetailState()

    data class Success(
        val restaurantEntity: RestaurantEntity,
        val restaurantFoodList: List<RestaurantFoodEntity>? = null,
        val foodMenuListInBasket: List<RestaurantFoodEntity>? = null,
        val isClearNeedInBasketAndAction: Pair<Boolean, () -> Unit> = Pair(false, {}),
        val isLiked: Boolean? = null,
    ) : RestaurantDetailState()
}