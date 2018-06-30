package android.benchmark.helpers

import android.benchmark.auth.*
import android.benchmark.eventbus.EventBusContainer
import android.benchmark.helpers.authentication.FacebookAuthentication
import android.benchmark.helpers.cache.EmptyLocalDataCache
import android.benchmark.helpers.cache.LocalDataCache
import android.benchmark.helpers.content.EmptyResourceManager
import android.benchmark.helpers.content.ResourceManager
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.databases.EmptyDatabase
import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.helpers.dataservices.datasource.DataSourceContainerImpl
import android.benchmark.ui.activities.main.fragments.EmptyFragmentChanger
import android.benchmark.ui.activities.main.fragments.FragmentChanger
import android.benchmark.ui.fragments.genericlist.MapperInstanceProvider
import android.benchmark.ui.utils.AppVersionProvider
import android.benchmark.ui.utils.EmptyAppVersionProvider

interface IServices {
    var resourceManager: ResourceManager
    var facebookAuthentication: FacebookAuthentication
    var dataCache: LocalDataCache
    var appVersionProvider: AppVersionProvider
    var googleAuth: GoogleAuth
    var eventBusContainer: EventBusContainer
    var dataSourceContainer: DataSourceContainer
    var auth: Auth
    var database: Database
    var mapperInstanceProvider: MapperInstanceProvider
    var fragmentChanger: FragmentChanger
}

class EmptyServices : IServices {
    override var resourceManager: ResourceManager = EmptyResourceManager()
    override var facebookAuthentication: FacebookAuthentication = FacebookAuthentication()
    override var dataCache: LocalDataCache = EmptyLocalDataCache()
    override var appVersionProvider: AppVersionProvider = EmptyAppVersionProvider()
    override var googleAuth: GoogleAuth = GoogleAuthEmpty()
    override var eventBusContainer: EventBusContainer = EventBusContainer()
    override var dataSourceContainer: DataSourceContainer = DataSourceContainerImpl()
    override var auth: Auth = AuthImpl(AuthUser.createEmpty())
    override var database: Database = EmptyDatabase()
    override var mapperInstanceProvider: MapperInstanceProvider = MapperInstanceProvider()
    override var fragmentChanger: FragmentChanger = EmptyFragmentChanger()
}

class Services {
    companion object {
        var instance: IServices = EmptyServices()
    }
}

class ServicesImpl(
        override var resourceManager: ResourceManager,
        override var facebookAuthentication: FacebookAuthentication,
        override var dataCache: LocalDataCache,
        override var appVersionProvider: AppVersionProvider,
        override var googleAuth: GoogleAuth,
        override var eventBusContainer: EventBusContainer,
        override var dataSourceContainer: DataSourceContainer,
        override var auth: Auth,
        override var database: Database,
        override var mapperInstanceProvider: MapperInstanceProvider,
        override var fragmentChanger: FragmentChanger) : IServices