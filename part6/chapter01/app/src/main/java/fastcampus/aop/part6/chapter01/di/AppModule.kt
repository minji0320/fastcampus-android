package fastcampus.aop.part6.chapter01.di

import fastcampus.aop.part6.chapter01.screen.main.home.HomeViewModel
import fastcampus.aop.part6.chapter01.screen.main.my.MyViewModel
import fastcampus.aop.part6.chapter01.util.provider.DefaultResourcesProvider
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // ViewModel
    viewModel { HomeViewModel() }
    viewModel { MyViewModel() }

    // Retrofit
    single { provideRetrofit() }

    // ResourceProvider
    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    // Coroutines
    single { Dispatchers.IO }
    single { Dispatchers.Main }

}