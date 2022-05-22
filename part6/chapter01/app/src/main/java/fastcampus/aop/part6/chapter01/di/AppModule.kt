package fastcampus.aop.part6.chapter01.di

import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.entity.MapSearchInfoEntity
import fastcampus.aop.part6.chapter01.data.entity.RestaurantEntity
import fastcampus.aop.part6.chapter01.data.repository.map.DefaultMapRepository
import fastcampus.aop.part6.chapter01.data.repository.map.MapRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.DefaultRestaurantRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.RestaurantRepository
import fastcampus.aop.part6.chapter01.data.repository.user.DefaultUserRepository
import fastcampus.aop.part6.chapter01.data.repository.user.UserRepository
import fastcampus.aop.part6.chapter01.screen.main.home.HomeViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantCategory
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantListViewModel
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.detail.RestaurantDetailViewModel
import fastcampus.aop.part6.chapter01.screen.main.my.MyViewModel
import fastcampus.aop.part6.chapter01.screen.mylocation.MyLocationViewModel
import fastcampus.aop.part6.chapter01.util.provider.DefaultResourcesProvider
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModel
    viewModel { HomeViewModel(get(), get()) }
    viewModel { MyViewModel() }
    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get())
    }
    viewModel { (mapSearchInfoEntity: MapSearchInfoEntity) ->
        MyLocationViewModel(mapSearchInfoEntity, get(), get())
    }
    viewModel { (restaurantEntity: RestaurantEntity) -> RestaurantDetailViewModel(restaurantEntity) }

    // Repository
    single<RestaurantRepository> { DefaultRestaurantRepository(get(), get(), get()) }
    single<MapRepository> { DefaultMapRepository(get(), get()) }
    single<UserRepository> { DefaultUserRepository(get(), get()) }

    // Retrofit
    single { provideMapRetrofit() }

    // ApiService
    single { provideMapApiService(get()) }

    // DB
    single { provideDB(androidApplication()) }
    single { provideLocationDao(get()) }

    // ResourceProvider
    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    // Coroutines
    single { Dispatchers.IO }
    single { Dispatchers.Main }

}