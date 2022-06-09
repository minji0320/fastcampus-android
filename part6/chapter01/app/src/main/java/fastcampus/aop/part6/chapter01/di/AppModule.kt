package fastcampus.aop.part6.chapter01.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.entity.MapSearchInfoEntity
import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity
import fastcampus.aop.part6.chapter01.data.entity.RestaurantFoodEntity
import fastcampus.aop.part6.chapter01.data.preference.AppPreferenceManager
import fastcampus.aop.part6.chapter01.data.repository.map.DefaultMapRepository
import fastcampus.aop.part6.chapter01.data.repository.map.MapRepository
import fastcampus.aop.part6.chapter01.data.repository.order.DefaultOrderRepository
import fastcampus.aop.part6.chapter01.data.repository.order.OrderRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.DefaultRestaurantRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.RestaurantRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.food.DefaultRestaurantFoodRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.review.DefaultRestaurantReviewRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.review.RestaurantReviewRepository
import fastcampus.aop.part6.chapter01.data.repository.user.DefaultUserRepository
import fastcampus.aop.part6.chapter01.data.repository.user.UserRepository
import fastcampus.aop.part6.chapter01.screen.main.home.HomeViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantCategory
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantListViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.RestaurantDetailViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.menu.RestaurantMenuListViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.review.RestaurantReviewListViewModel
import fastcampus.aop.part6.chapter01.screen.main.like.RestaurantLikeListViewModel
import fastcampus.aop.part6.chapter01.screen.main.my.MyViewModel
import fastcampus.aop.part6.chapter01.screen.mylocation.MyLocationViewModel
import fastcampus.aop.part6.chapter01.screen.order.OrderMenuListViewModel
import fastcampus.aop.part6.chapter01.screen.review.gallery.GalleryPhotoRepository
import fastcampus.aop.part6.chapter01.screen.review.gallery.GalleryViewModel
import fastcampus.aop.part6.chapter01.util.event.MenuChangeEventBus
import fastcampus.aop.part6.chapter01.util.provider.DefaultResourcesProvider
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    // ViewModel
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { RestaurantLikeListViewModel(get()) }
    viewModel { MyViewModel(get(), get(), get(), get()) }
    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get())
    }
    viewModel { (mapSearchInfoEntity: MapSearchInfoEntity) ->
        MyLocationViewModel(mapSearchInfoEntity, get(), get())
    }
    viewModel { (restaurantEntity: RestaurantEntity) ->
        RestaurantDetailViewModel(restaurantEntity, get(), get())
    }
    viewModel { (restaurantId: Long, foodEntityList: List<RestaurantFoodEntity>) ->
        RestaurantMenuListViewModel(restaurantId, foodEntityList, get())
    }
    viewModel { (restaurantTitle: String) -> RestaurantReviewListViewModel(restaurantTitle, get()) }
    viewModel { (firebaseAuth: FirebaseAuth) -> OrderMenuListViewModel(get(), get(), firebaseAuth) }
    viewModel { GalleryViewModel(get()) }

    // Repository
    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }
    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get(), get()) }
    single<RestaurantFoodRepository> { DefaultRestaurantFoodRepository(get(), get(), get()) }
    single<RestaurantReviewRepository> { DefaultRestaurantReviewRepository(get(), get()) }
    single<OrderRepository> { DefaultOrderRepository(get(), get()) }
    single { GalleryPhotoRepository(androidContext()) }


    // Retrofit
    single { provideGsonConvertFactory() }
    single { buildOkHttpClient() }
    single(named("map")) { provideMapRetrofit(get(), get()) }
    single(named("food")) { provideFoodRetrofit(get(), get()) }

    // ApiService
    single { provideMapApiService(get(qualifier = named("map"))) }
    single { provideFoodApiService(get(qualifier = named("food"))) }

    // DB
    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }
    single { provideRestaurantDao(get()) }
    single { provideFoodMenuBasketDao(get()) }

    // ResourceProvider
    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    // PreferenceManager
    single { AppPreferenceManager(androidContext()) }

    // Coroutines
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    // MenuChangeEventBus
    single { MenuChangeEventBus() }

    // Firestore
    single { Firebase.firestore }
    single { FirebaseAuth.getInstance() }
    single { FirebaseStorage.getInstance() }

}