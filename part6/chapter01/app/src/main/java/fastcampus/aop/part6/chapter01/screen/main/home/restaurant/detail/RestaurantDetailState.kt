package fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail

import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity

sealed class RestaurantDetailState {

    object Uninitialized : RestaurantDetailState()

    object Loading : RestaurantDetailState()

    data class Success(
        val restaurantEntity: RestaurantEntity,
        val isLiked: Boolean? = null
    ) : RestaurantDetailState()
}