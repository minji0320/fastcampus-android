package fastcampus.aop.part5.chapter02.di

import fastcampus.aop.part5.chapter02.data.network.buildOkHttpClient
import fastcampus.aop.part5.chapter02.data.network.provideGsonConverterFactory
import fastcampus.aop.part5.chapter02.data.network.provideProductApiService
import fastcampus.aop.part5.chapter02.data.network.provideProductRetrofit
import fastcampus.aop.part5.chapter02.data.repository.DefaultProductRepository
import fastcampus.aop.part5.chapter02.data.repository.ProductRepository
import fastcampus.aop.part5.chapter02.domain.GetProductItemUseCase
import fastcampus.aop.part5.chapter02.domain.GetProductListUseCase
import fastcampus.aop.part5.chapter02.presentation.list.ProductListFragment
import fastcampus.aop.part5.chapter02.presentation.list.ProductListViewModel
import fastcampus.aop.part5.chapter02.presentation.main.MainViewModel
import fastcampus.aop.part5.chapter02.presentation.profile.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModels
    viewModel { MainViewModel() }
    viewModel { ProductListViewModel(get()) }
    viewModel { ProfileViewModel() }

    // Coroutines Dispatcher
    single { Dispatchers.Main }
    single { Dispatchers.IO }

    // UseCases
    factory { GetProductItemUseCase(get()) }
    factory { GetProductListUseCase(get()) }

    // Repositories
    single<ProductRepository> { DefaultProductRepository(get(), get()) }

    single { provideGsonConverterFactory() }

    single { buildOkHttpClient() }

    single { provideProductRetrofit(get(), get()) }

    single { provideProductApiService(get()) }

}