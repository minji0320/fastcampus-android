package fastcampus.aop.part5.chapter02.di

import fastcampus.aop.part5.chapter02.data.db.provideDB
import fastcampus.aop.part5.chapter02.data.db.provideProductDao
import fastcampus.aop.part5.chapter02.data.network.buildOkHttpClient
import fastcampus.aop.part5.chapter02.data.network.provideGsonConverterFactory
import fastcampus.aop.part5.chapter02.data.network.provideProductApiService
import fastcampus.aop.part5.chapter02.data.network.provideProductRetrofit
import fastcampus.aop.part5.chapter02.data.preference.PreferenceManager
import fastcampus.aop.part5.chapter02.data.repository.DefaultProductRepository
import fastcampus.aop.part5.chapter02.data.repository.ProductRepository
import fastcampus.aop.part5.chapter02.domain.DeleteOrderedProductListUseCase
import fastcampus.aop.part5.chapter02.domain.GetOrderedProductListUseCase
import fastcampus.aop.part5.chapter02.domain.GetProductItemUseCase
import fastcampus.aop.part5.chapter02.domain.GetProductListUseCase
import fastcampus.aop.part5.chapter02.domain.OrderProductItemUseCase
import fastcampus.aop.part5.chapter02.presentation.detail.ProductDetailViewModel
import fastcampus.aop.part5.chapter02.presentation.list.ProductListViewModel
import fastcampus.aop.part5.chapter02.presentation.main.MainViewModel
import fastcampus.aop.part5.chapter02.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel(get()) }
    viewModel { ProfileViewModel(get(), get(), get()) }
    viewModel { (productId: Long) -> ProductDetailViewModel(productId, get(), get()) }

    // Coroutines Dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // UseCases
    factory { GetProductItemUseCase(get()) }
    factory { GetProductListUseCase(get()) }
    factory { OrderProductItemUseCase(get()) }
    factory { GetOrderedProductListUseCase(get()) }
    factory { DeleteOrderedProductListUseCase(get()) }

    // Repositories
    single<ProductRepository> { DefaultProductRepository(get(), get(), get()) }

    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }

    single { PreferenceManager(androidContext()) }

    // Database
    single { provideDB(androidApplication()) }
    single { provideProductDao(get()) }

}