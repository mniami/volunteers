package android.androidkotlinbenchmark

import android.app.Application
import android.benchmark.auth.AuthImpl
import android.benchmark.auth.AuthUser
import android.benchmark.auth.GoogleAuthImpl
import android.benchmark.auth.SignInAuthResult
import android.benchmark.eventbus.EventBusContainer

import android.benchmark.helpers.Services
import android.benchmark.helpers.ServicesImpl
import android.benchmark.helpers.authentication.FacebookAuthentication
import android.benchmark.helpers.cache.AndroidLocalDataCache
import android.benchmark.helpers.content.AndroidResourceManager

import android.benchmark.helpers.databases.FirebaseDatabaseImpl
import android.benchmark.helpers.dataservices.datasource.DataSourceContainerImpl
import android.benchmark.helpers.dataservices.datasource.UserDataSource
import android.benchmark.helpers.dataservices.datasource.VolunteersDataSource

import android.benchmark.ui.utils.AppVersionProviderImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val resourceManager = AndroidResourceManager(this)
        val auth = AuthImpl(AuthUser.createEmpty())
        val database = FirebaseDatabaseImpl(auth)

        Services.instance = ServicesImpl(
                resourceManager = resourceManager,
                dataCache = AndroidLocalDataCache(baseContext),
                appVersionProvider = AppVersionProviderImpl(packageManager, packageName),
                facebookAuthentication = FacebookAuthentication(),
                eventBusContainer = EventBusContainer(),
                dataSourceContainer = DataSourceContainerImpl(),
                googleAuth = GoogleAuthImpl(auth, SignInAuthResult.createEmpty()),
                database = database,
                auth = auth)

        val dataSources = listOf(
                VolunteersDataSource(resourceManager, database),
                UserDataSource(Services.instance.database, Services.instance.auth))

        for (dataSource in dataSources) {
            Services.instance.dataSourceContainer.putDataSource(dataSource)
        }
    }
}