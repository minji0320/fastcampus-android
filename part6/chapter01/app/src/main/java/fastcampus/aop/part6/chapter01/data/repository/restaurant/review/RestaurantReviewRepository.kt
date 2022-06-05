package fastcampus.aop.part6.chapter01.data.repository.restaurant.review

import fastcampus.aop.part6.chapter01.data.entity.ReviewEntity

interface RestaurantReviewRepository {

    suspend fun getReviews(restaurantTitle: String): DefaultRestaurantReviewRepository.Result
}