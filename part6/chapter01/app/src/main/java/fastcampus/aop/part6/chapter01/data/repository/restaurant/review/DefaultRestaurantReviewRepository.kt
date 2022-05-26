package fastcampus.aop.part6.chapter01.data.repository.restaurant.review

import fastcampus.aop.part6.chapter01.data.entity.RestaurantReviewEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DefaultRestaurantReviewRepository(
    private val ioDispatcher: CoroutineDispatcher,
) : RestaurantReviewRepository {

    override suspend fun getReviews(restaurantTitle: String): List<RestaurantReviewEntity> =
        withContext(ioDispatcher) {
            return@withContext (0..10).map {
                RestaurantReviewEntity(
                    id = it.toLong(),
                    title = "제목 $it",
                    description = "내용 $it",
                    grade = (1 until 5).random()
                )
            }
        }
}