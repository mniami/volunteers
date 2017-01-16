package android.benchmark.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class
))
interface ApplicationComponent {

}