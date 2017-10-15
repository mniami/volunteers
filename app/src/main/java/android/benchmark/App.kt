package android.androidkotlinbenchmark

import android.app.Application
import android.benchmark.DependencyModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DependencyModule().init(this)
    }
}