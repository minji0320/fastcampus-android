package fastcampus.aop.part6.chapter01.di

import fastcampus.aop.part6.chapter01.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(provideGsonConvertFactory())
        .client(buildOkHttpClient())
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