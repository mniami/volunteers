package android.benchmark.helpers

import android.benchmark.auth.*
import android.benchmark.eventbus.EventBusContainer
import android.benchmark.helpers.authentication.FacebookAuthentication
import android.benchmark.helpers.cache.EmptyLocalDataCache
import android.benchmark.helpers.cache.LocalDataCache
import android.benchmark.helpers.content.EmptyResourceManager
import android.benchmark.helpers.content.ResourceManager
import android.benchmark.helpers.databases.FirebaseDatabaseImpl
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.databases.EmptyDatabase
import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.helpers.dataservices.datasource.DataSourceContainerImpl
import android.benchmark.ui.utils.AppVersionProvider
import android.benchmark.ui.utils.EmptyAppVersionProvider

interface IServices {
    val resourceManager: ResourceManager
    val facebookAuthentication: FacebookAuthentication
    val dataCache: LocalDataCache
    val appVersionProvider: AppVersionProvider
    val googleAuth : GoogleAuth
    val eventBusContainer: EventBusContainer
    val dataSourceContainer: DataSourceContainer
    val auth : Auth
    val database : Database
}

class EmptyServices : IServices {
    override val resourceManager: ResourceManager = EmptyResourceManager()
    override val facebookAuthentication: FacebookAuthentication = FacebookAuthentication()
    override val dataCache: LocalDataCache = EmptyLocalDataCache()
    override val appVersionProvider: AppVersionProvider = EmptyAppVersionProvider()
    override val googleAuth: GoogleAuth = GoogleAuthEmpty()
    override val eventBusContainer: EventBusContainer = EventBusContainer()
    override val dataSourceContainer: DataSourceContainer = DataSourceContainerImpl()
    override val auth: Auth = AuthImpl(AuthUser.createEmpty())
    override val database: Database = EmptyDatabase()
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
        override val googleAuth: GoogleAuth,
        override val eventBusContainer: EventBusContainer,
        override val dataSourceContainer: DataSourceContainer,
        override val auth: Auth,
        override val database : Database) : IServices