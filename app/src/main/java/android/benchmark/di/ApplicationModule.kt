package android.benchmark.di

import android.benchmark.App
import android.benchmark.di.qualifier.ApplicationQualifier
import android.content.Context
import com.antonioleiva.bandhookkotlin.di.qualifier.LanguageSelection
import dagger.Module
import dagger.Provides
import java.util.*
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App) {

    @Provides @Singleton
    fun provideApplication(): App = app

    @Provides @Singleton @ApplicationQualifier
    fun provideApplicationContext(): Context = app

    @Provides @Singleton @LanguageSelection
    fun provideLanguageSelection(): String = Locale.getDefault().language
}