package fastcampus.aop.part5.chapter07.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import fastcampus.aop.part5.chapter07.domain.model.Movie
import fastcampus.aop.part5.chapter07.domain.model.Review
import kotlinx.coroutines.tasks.await

class ReviewFirestoreApi(
    private val firestore: FirebaseFirestore,
) : ReviewApi {

    override suspend fun getLatestReview(movieId: String): Review? =
        firestore.collection("reviews")
            .whereEqualTo("movieId", movieId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .limit(1)
            .get()
            .await()
            .map { it.toObject<Review>() }
            .firstOrNull()

    override suspend fun getAllMovieReviews(movieId: String): List<Review> =
        firestore.collection("reviews")
            .whereEqualTo("movieId", movieId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()
            .map { it.toObject() }

    override suspend fun getAllUserReviews(userId: String): List<Review> =
        firestore.collection("reviews")
            .whereEqualTo("userId", userId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .await()
            .map { it.toObject() }

    override suspend fun addReview(review: Review): Review {
        val newReviewPreference = firestore.collection("reviews").document()
        val moviePreference = firestore.collection("movies").document(review.movieId!!)

        firestore.runTransaction { transition ->
            val movie = transition.get(moviePreference).toObject<Movie>()!!

            val oldAverageScore = movie.averageScore ?: 0f
            val oldNumberOfScore = movie.numberOfScore ?: 0
            val oldTotalScore = oldAverageScore * oldNumberOfScore

            val newNumberOfScore = oldNumberOfScore + 1
            val newAverageScore = (oldTotalScore + (review.score ?: 0f)) / newNumberOfScore

            transition.set(
                moviePreference,
                movie.copy(
                    numberOfScore = newNumberOfScore,
                    averageScore = newAverageScore
                )
            )

            transition.set(
                newReviewPreference,
                review,
                SetOptions.merge()
            )
        }.await()

        return newReviewPreference.get().await().toObject<Review>()!!
    }

    override suspend fun removeReview(review: Review) {
        val reviewReference = firestore.collection("reviews").document(review.id!!)
        val movieReference = firestore.collection("movies").document(review.movieId!!)

        firestore.runTransaction { transaction ->
            val movie = transaction
                .get(movieReference)
                .toObject<Movie>()!!

            val oldAverageScore = movie.averageScore ?: 0f
            val oldNumberOfScore = movie.numberOfScore ?: 0
            val oldTotalScore = oldAverageScore * oldNumberOfScore

            val newNumberOfScore = (oldNumberOfScore - 1).coerceAtLeast(0)
            val newAverageScore = if (newNumberOfScore > 0) {
                (oldTotalScore - (review.score ?: 0f)) / newNumberOfScore
            } else {
                0f
            }

            transaction.set(
                movieReference,
                movie.copy(
                    numberOfScore = newNumberOfScore,
                    averageScore = newAverageScore
                )
            )

            transaction.delete(reviewReference)
        }.await()
    }
}