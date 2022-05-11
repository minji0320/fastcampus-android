package fastcampus.aop.part5.chapter07.di

import android.app.Activity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import fastcampus.aop.part5.chapter07.data.api.*
import fastcampus.aop.part5.chapter07.data.preference.PreferenceManager
import fastcampus.aop.part5.chapter07.data.preference.SharedPreferenceManager
import fastcampus.aop.part5.chapter07.data.repository.*
import fastcampus.aop.part5.chapter07.domain.model.Movie
import fastcampus.aop.part5.chapter07.domain.usecase.GetAllMoviesUseCase
import fastcampus.aop.part5.chapter07.domain.usecase.GetAllMovieReviewsUseCase
import fastcampus.aop.part5.chapter07.domain.usecase.GetMyReviewedMoviesUseCase
import fastcampus.aop.part5.chapter07.domain.usecase.GetRandomFeaturedMovieUseCase
import fastcampus.aop.part5.chapter07.presentation.home.HomeContract
import fastcampus.aop.part5.chapter07.presentation.home.HomeFragment
import fastcampus.aop.part5.chapter07.presentation.home.HomePresenter
import fastcampus.aop.part5.chapter07.presentation.reviews.MovieReviewsContract
import fastcampus.aop.part5.chapter07.presentation.reviews.MovieReviewsFragment
import fastcampus.aop.part5.chapter07.presentation.reviews.MovieReviewsPresenter
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { Dispatchers.IO }
}

val dataModule = module {
    single { Firebase.firestore }

    single<MovieApi> { MovieFirestoreApi(get()) }
    single<ReviewApi> { ReviewFirestoreApi(get()) }
    single<UserApi> { UserFirestoreApi(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<ReviewRepository> { ReviewRepositoryImpl(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }

    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }
}

val domainModule = module {
    factory { GetAllMoviesUseCase(get()) }
    factory { GetAllMovieReviewsUseCase(get()) }
    factory { GetRandomFeaturedMovieUseCase(get(), get()) }
    factory { GetMyReviewedMoviesUseCase(get(), get(), get()) }
}

val presentationModule = module {
    scope<HomeFragment> {
        scoped<HomeContract.Presenter> { HomePresenter(getSource(), get(), get()) }
    }
    scope<MovieReviewsFragment> {
        scoped<MovieReviewsContract.Presenter> { (movie: Movie) ->
            MovieReviewsPresenter(movie, getSource(), get())
        }
    }
}