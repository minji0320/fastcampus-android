package fastcampus.aop.part6.chapter01.di

import fastcampus.aop.part6.chapter01.util.provider.DefaultResourcesProvider
import fastcampus.aop.part6.chapter01.util.provider.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    // Retrofit
    single { provideRetrofit() }

    // ResourceProvider
    single<ResourcesProvider> { DefaultResourcesProvider(androidContext()) }

    // Coroutines
    single { Dispatchers.IO }
    single { Dispatchers.Main }

}