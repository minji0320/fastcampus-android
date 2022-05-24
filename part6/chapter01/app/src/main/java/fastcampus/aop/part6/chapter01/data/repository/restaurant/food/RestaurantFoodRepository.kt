package fastcampus.aop.part6.chapter01.data.repository.restaurant.food

import fastcampus.aop.part6.chapter01.data.entity.RestaurantFoodEntity

interface RestaurantFoodRepository {

    suspend fun getFoods(restaurantId: Long): List<RestaurantFoodEntity>
}