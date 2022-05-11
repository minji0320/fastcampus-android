package fastcampus.aop.part5.chapter07.data.api

import fastcampus.aop.part5.chapter07.domain.model.Review

interface ReviewApi {

    suspend fun getLatestReview(movieId: String): Review?

    suspend fun getAllMovieReviews(movieId: String): List<Review>

    suspend fun getAllUserReviews(userId: String): List<Review>

}