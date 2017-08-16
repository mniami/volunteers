package android.benchmark

import android.app.Application
import android.benchmark.eventbus.EventBusContainer
import android.benchmark.helpers.Services
import android.benchmark.helpers.ServicesImpl
import android.benchmark.helpers.authentication.FacebookAuthentication
import android.benchmark.helpers.cache.AndroidLocalDataCache
import android.benchmark.helpers.content.AndroidResourceManager
import android.benchmark.helpers.dataservices.datasource.DataSourceContainerImpl
import android.benchmark.helpers.dataservices.datasource.VolunteersDataSource
import android.benchmark.ui.utils.AppVersionProviderImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val resourceManager = AndroidResourceManager(this)

        Services.instance = ServicesImpl(
                resourceManager = resourceManager,
                dataCache = AndroidLocalDataCache(baseContext),
                appVersionProvider = AppVersionProviderImpl(packageManager, packageName),
                facebookAuthentication = FacebookAuthentication(),
                eventBusContainer = EventBusContainer(),
                dataSourceContainer = DataSourceContainerImpl())

        val dataSources = listOf(VolunteersDataSource(resourceManager))
        for (dataSource in dataSources) {
            Services.instance.dataSourceContainer.putDataSource(dataSource)
        }
    }
}