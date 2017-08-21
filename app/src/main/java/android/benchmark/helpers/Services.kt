package android.benchmark.helpers

import android.benchmark.auth.GoogleAuthImpl
import android.benchmark.eventbus.EventBusContainer
import android.benchmark.helpers.authentication.FacebookAuthentication
import android.benchmark.helpers.cache.EmptyLocalDataCache
import android.benchmark.helpers.cache.LocalDataCache
import android.benchmark.helpers.content.EmptyResourceManager
import android.benchmark.helpers.content.ResourceManager
import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.helpers.dataservices.datasource.DataSourceContainerImpl
import android.benchmark.ui.utils.AppVersionProvider
import android.benchmark.ui.utils.EmptyAppVersionProvider

interface IServices {
    val resourceManager: ResourceManager
    val facebookAuthentication: FacebookAuthentication
    val dataCache: LocalDataCache
    val appVersionProvider: AppVersionProvider
    val googleAuth : GoogleAuthImpl
    val eventBusContainer: EventBusContainer
    val dataSourceContainer: DataSourceContainer
}

class EmptyServices : IServices {
    override val resourceManager: ResourceManager = EmptyResourceManager()
    override val facebookAuthentication: FacebookAuthentication = FacebookAuthentication()
    override val dataCache: LocalDataCache = EmptyLocalDataCache()
    override val appVersionProvider: AppVersionProvider = EmptyAppVersionProvider()
    override val googleAuth: GoogleAuthImpl = GoogleAuthImpl()
    override val eventBusContainer: EventBusContainer = EventBusContainer()
    override val dataSourceContainer: DataSourceContainer = DataSourceContainerImpl()
}

class Services {
    companion object {
        var instance: IServices = EmptyServices()
    }
}

class ServicesImpl(
        override val resourceManager: ResourceManager,
        override val facebookAuthentication: FacebookAuthentication,
        override val dataCache: LocalDataCache,
        override val appVersionProvider: AppVersionProvider,
        override val googleAuth: GoogleAuthImpl,
        override val eventBusContainer: EventBusContainer,
        override val dataSourceContainer: DataSourceContainer) : IServices