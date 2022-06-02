package fastcampus.aop.part6.chapter01.data.response.restaurant

import fastcampus.aop.part6.chapter01.data.entity.RestaurantFoodEntity

data class RestaurantFoodResponse(
    val id: String,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
) {

    fun toEntity(restaurantId: Long, restaurantTitle: String) = RestaurantFoodEntity(
        id, title, description, price.toDouble().toInt(), imageUrl, restaurantId, restaurantTitle
    )
}