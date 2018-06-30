package guideme.volunteers.helpers

import guideme.volunteers.auth.*
import guideme.volunteers.eventbus.EventBusContainer
import guideme.volunteers.helpers.authentication.FacebookAuthentication
import guideme.volunteers.helpers.cache.EmptyLocalDataCache
import guideme.volunteers.helpers.cache.LocalDataCache
import guideme.volunteers.helpers.content.EmptyResourceManager
import guideme.volunteers.helpers.content.ResourceManager
import guideme.volunteers.helpers.dataservices.databases.Database
import guideme.volunteers.helpers.dataservices.databases.EmptyDatabase
import guideme.volunteers.helpers.dataservices.datasource.DataSourceContainer
import guideme.volunteers.helpers.dataservices.datasource.DataSourceContainerImpl
import guideme.volunteers.ui.activities.main.fragments.EmptyFragmentChanger
import guideme.volunteers.ui.activities.main.fragments.FragmentChanger
import guideme.volunteers.ui.fragments.genericlist.MapperInstanceProvider
import guideme.volunteers.ui.utils.AppVersionProvider
import guideme.volunteers.ui.utils.EmptyAppVersionProvider

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

var Container : IServices = EmptyServices()

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