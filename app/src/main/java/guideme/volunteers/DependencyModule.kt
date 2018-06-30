package guideme.volunteers

import guideme.volunteers.auth.AuthImpl
import guideme.volunteers.auth.AuthUser
import guideme.volunteers.helpers.Services
import guideme.volunteers.helpers.content.AndroidResourceManager
import guideme.volunteers.helpers.databases.FirebaseDatabaseImpl
import guideme.volunteers.helpers.databases.MockDatabaseImpl
import guideme.volunteers.helpers.dataservices.datasource.DataSourceContainerImpl
import guideme.volunteers.helpers.dataservices.datasource.MockUserDataSource
import guideme.volunteers.helpers.dataservices.datasource.MockVolunteersDataSource
import guideme.volunteers.log.Logger
import guideme.volunteers.ui.activities.main.fragments.FragmentChangerImpl
import guideme.volunteers.ui.fragments.KnownMappers
import guideme.volunteers.ui.fragments.genericlist.MapperInstanceProvider
import guideme.volunteers.ui.fragments.volunteer.details.VolunteerGenericItemMap

class DependencyModule {
    fun init(app: App) {
        val resourceManager = AndroidResourceManager(app)
        val auth = AuthImpl(AuthUser.createEmpty())
        val database = FirebaseDatabaseImpl(auth)
        val mapperInstanceProvider = MapperInstanceProvider()
        val dataContainer = DataSourceContainerImpl()
        val fragmentChanger = FragmentChangerImpl(dataSourceContainer = dataContainer)

        Logger.instance = AndroidLogger()
        mapperInstanceProvider.register(KnownMappers.volunteers, VolunteerGenericItemMap(fragmentChanger))

//        Services.instance = ServicesImpl(
//                resourceManager,
//                FacebookAuthentication(),
//                AndroidLocalDataCache(app.baseContext),
//                AppVersionProviderImpl(app.packageManager, app.packageName),
//                GoogleAuthImpl(auth, SignInAuthResult.createEmpty()),
//                EventBusContainer(),
//                dataContainer,
//                auth,
//                database,
//                mapperInstanceProvider,
//                fragmentChanger)
        Services.instance.database = MockDatabaseImpl()
        Services.instance.fragmentChanger = fragmentChanger
        Services.instance.mapperInstanceProvider = mapperInstanceProvider
        Services.instance.resourceManager = resourceManager
        Services.instance.dataSourceContainer = dataContainer

        val dataSources = listOf(
                //VolunteerDataSourceImpl(database),
                MockVolunteersDataSource(),
                //UserDataSourceImpl(Services.instance.database, Services.instance.auth)
                MockUserDataSource())

        for (dataSource in dataSources) {
            Services.instance.dataSourceContainer.putDataSource(dataSource)
        }
    }
}