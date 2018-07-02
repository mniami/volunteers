package guideme.volunteers

import guideme.volunteers.auth.AuthImpl
import guideme.volunteers.auth.AuthUser
import guideme.volunteers.auth.GoogleAuthImpl
import guideme.volunteers.auth.SignInAuthResult
import guideme.volunteers.eventbus.EventBusContainer
import guideme.volunteers.helpers.Container
import guideme.volunteers.helpers.ServicesImpl
import guideme.volunteers.helpers.authentication.FacebookAuthentication
import guideme.volunteers.helpers.cache.AndroidLocalDataCache
import guideme.volunteers.helpers.content.AndroidResourceManager
import guideme.volunteers.helpers.databases.FirebaseDatabaseImpl
import guideme.volunteers.helpers.databases.MockDatabaseImpl
import guideme.volunteers.helpers.dataservices.datasource.*
import guideme.volunteers.log.Logger
import guideme.volunteers.ui.activities.main.fragments.FragmentChangerImpl
import guideme.volunteers.ui.fragments.KnownMappers
import guideme.volunteers.ui.fragments.genericlist.MapperInstanceProvider
import guideme.volunteers.ui.fragments.volunteer.details.VolunteerGenericItemMap
import guideme.volunteers.ui.utils.AppVersionProviderImpl

class DependencyModule {
    fun init(app: App) {
        initProduction(app)
    }

    private fun initProduction(app: App){
        val resourceManager = AndroidResourceManager(app)
        val auth = AuthImpl(AuthUser.createEmpty())
        val database = FirebaseDatabaseImpl()
        val mapperInstanceProvider = MapperInstanceProvider()
        val dataContainer = DataSourceContainerImpl()
        val fragmentChanger = FragmentChangerImpl(dataSourceContainer = dataContainer)

        Logger.instance = AndroidLogger()
        mapperInstanceProvider.register(KnownMappers.volunteers, VolunteerGenericItemMap(fragmentChanger))

        Container = ServicesImpl(
                resourceManager,
                FacebookAuthentication(),
                AndroidLocalDataCache(app.baseContext),
                AppVersionProviderImpl(app.packageManager, app.packageName),
                GoogleAuthImpl(auth, SignInAuthResult.createEmpty()),
                EventBusContainer(),
                dataContainer,
                auth,
                database,
                mapperInstanceProvider,
                fragmentChanger)

        val dataSources = listOf(
                VolunteerDataSourceImpl(database),
                UserDataSourceImpl(Container.database, Container.auth))

        for (dataSource in dataSources) {
            Container.dataSourceContainer.putDataSource(dataSource)
        }
    }

    private fun initMock(app: App){
        val resourceManager = AndroidResourceManager(app)
        val mapperInstanceProvider = MapperInstanceProvider()
        val dataContainer = DataSourceContainerImpl()
        val fragmentChanger = FragmentChangerImpl(dataSourceContainer = dataContainer)

        Logger.instance = AndroidLogger()
        mapperInstanceProvider.register(KnownMappers.volunteers, VolunteerGenericItemMap(fragmentChanger))

        Container.database = MockDatabaseImpl()
        Container.fragmentChanger = fragmentChanger
        Container.mapperInstanceProvider = mapperInstanceProvider
        Container.resourceManager = resourceManager
        Container.dataSourceContainer = dataContainer

        val dataSources = listOf(
                MockVolunteersDataSource(),
                MockUserDataSource())

        for (dataSource in dataSources) {
            Container.dataSourceContainer.putDataSource(dataSource)
        }
    }
}