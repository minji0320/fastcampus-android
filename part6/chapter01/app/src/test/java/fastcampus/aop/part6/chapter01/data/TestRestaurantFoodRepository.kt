package fastcampus.aop.part6.chapter01.data

import fastcampus.aop.part6.chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.part6.chapter01.data.repository.restaurant.food.RestaurantFoodRepository

class TestRestaurantFoodRepository : RestaurantFoodRepository {

    private val foodMenuListInBasket = mutableListOf<RestaurantFoodEntity>()

    override suspend fun getFoods(
        restaurantId: Long,
        restaurantTitle: String,
    ): List<RestaurantFoodEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getFoodMenuListInBasket(restaurantId: Long): List<RestaurantFoodEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllFoodMenuListInBasket(): List<RestaurantFoodEntity> {
        return foodMenuListInBasket
    }

    override suspend fun insertFoodMenuInBasket(restaurantFoodEntity: RestaurantFoodEntity) {
        foodMenuListInBasket.add(restaurantFoodEntity)
    }

    override suspend fun removeFoodMenuListInBasket(foodId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun clearFoodMenuListInBasket() {
        foodMenuListInBasket.clear()
    }
}