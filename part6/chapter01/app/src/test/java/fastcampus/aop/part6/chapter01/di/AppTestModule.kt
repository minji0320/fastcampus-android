package fastcampus.aop.part6.chapter01.di

import com.google.firebase.auth.FirebaseAuth
import fastcampus.aop.part6.chapter01.data.TestOrderRepository
import fastcampus.aop.part6.chapter01.data.TestRestaurantFoodRepository
import fastcampus.aop.part6.chapter01.data.TestRestaurantRepository
import fastcampus.aop.part6.chapter01.data.entity.LocationLatLngEntity
import fastcampus.aop.part6.chapter01.data.repository.order.OrderRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.RestaurantRepository
import fastcampus.aop.part6.chapter01.data.repository.restaurant.food.RestaurantFoodRepository
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantCategory
import fastcampus.aop.part6.chapter01.screen.main.home.restaurant.RestaurantListViewModel
import fastcampus.aop.part6.chapter01.screen.order.OrderMenuListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {

    viewModel { (restaurantCategory: RestaurantCategory, locationLatLng: LocationLatLngEntity) ->
        RestaurantListViewModel(restaurantCategory, locationLatLng, get())
    }
    viewModel { (firebaseAuth: FirebaseAuth) -> OrderMenuListViewModel(get(), get(), firebaseAuth) }

    single<RestaurantRepository> { TestRestaurantRepository() }
    single<RestaurantFoodRepository> { TestRestaurantFoodRepository() }
    single<OrderRepository> { TestOrderRepository() }
}