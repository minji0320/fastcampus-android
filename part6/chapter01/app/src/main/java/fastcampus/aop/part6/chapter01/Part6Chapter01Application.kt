package fastcampus.aop.part6.chapter01

import android.app.Application
import android.content.Context
import fastcampus.aop.part6.chapter01.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Part6Chapter01Application : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        startKoin {
            androidContext(this@Part6Chapter01Application)

            modules(appModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}