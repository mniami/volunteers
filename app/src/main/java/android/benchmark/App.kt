package android.benchmark

import android.app.Application
import android.benchmark.di.ApplicationComponent
import android.benchmark.di.ApplicationModule

class App : Application() {

    companion object {
        lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger() {
//        graph = DaggerApplicationComponent.builder()
//                .applicationModule(ApplicationModule(this))
//                .build()
    }
}

