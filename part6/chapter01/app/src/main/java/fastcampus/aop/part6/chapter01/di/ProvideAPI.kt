package fastcampus.aop.part6.chapter01.di

import fastcampus.aop.part6.chapter01.BuildConfig
import fastcampus.aop.part6.chapter01.data.network.FoodApiService
import fastcampus.aop.part6.chapter01.data.network.MapApiService
import fastcampus.aop.part6.chapter01.data.url.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideMapApiService(retrofit: Retrofit): MapApiService {
    return retrofit.create(MapApiService::class.java)
}

fun provideFoodApiService(retrofit: Retrofit): FoodApiService {
    return retrofit.create(FoodApiService::class.java)
}

fun provideMapRetrofit(
    gsonConverterFactory: GsonConverterFactory,
    okHttpClient: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.TMAP_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideFoodRetrofit(
    gsonConverterFactory: GsonConverterFactory,
    okHttpClient: OkHttpClient,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.FOOD_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideGsonConvertFactory(): GsonConverterFactory =
    GsonConverterFactory.create()

fun buildOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            Level.BODY
        } else {
            Level.NONE
        }
    }

    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}