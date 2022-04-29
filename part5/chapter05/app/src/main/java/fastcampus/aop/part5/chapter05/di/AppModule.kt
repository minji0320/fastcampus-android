package fastcampus.aop.part5.chapter05.di

import android.app.Activity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import fastcampus.aop.part5.chapter05.data.api.StationApi
import fastcampus.aop.part5.chapter05.data.api.StationStorageApi
import fastcampus.aop.part5.chapter05.data.db.AppDatabase
import fastcampus.aop.part5.chapter05.data.preference.PreferenceManager
import fastcampus.aop.part5.chapter05.data.preference.SharedPreferenceManager
import fastcampus.aop.part5.chapter05.data.repository.StationRepository
import fastcampus.aop.part5.chapter05.data.repository.StationRepositoryImpl
import fastcampus.aop.part5.chapter05.presentation.stations.StationsContract
import fastcampus.aop.part5.chapter05.presentation.stations.StationsFragment
import fastcampus.aop.part5.chapter05.presentation.stations.StationsPresenter
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single { Dispatchers.IO }

    // Database
    single { AppDatabase.build(androidApplication()) }
    single { get<AppDatabase>().stationDao() }

    // Preference
    single { androidContext().getSharedPreferences("preference", Activity.MODE_PRIVATE) }
    single<PreferenceManager> { SharedPreferenceManager(get()) }

    // Api
    single<StationApi> { StationStorageApi(Firebase.storage) }

    // Repository
    single<StationRepository> { StationRepositoryImpl(get(), get(), get(), get()) }

    // Presentation
    // 프레그먼트 기준으로 스코프 생성 -> presenter도 이의 라이프 사이클을 따름
    // scope 내에서는 데이터 공유 가능
    scope<StationsFragment> {
        scoped<StationsContract.Presenter> { StationsPresenter(getSource(), get()) }
    }

}