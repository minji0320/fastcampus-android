package fastcampus.aop.part5.chapter01

import android.app.Application
import fastcampus.aop.part5.chapter01.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AopPart5Chapter01Application: Application() {
    override fun onCreate() {
        super.onCreate()

        // Todo : Koin Trigger
        // module 만들기
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AopPart5Chapter01Application)
            // module은 di 패키지 내에 생성
            modules(appModule)
        }
    }
}