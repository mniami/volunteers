package android.benchmark

import android.androidkotlinbenchmark.App
import android.benchmark.auth.AuthImpl
import android.benchmark.auth.AuthUser
import android.benchmark.helpers.Services
import android.benchmark.helpers.content.AndroidResourceManager
import android.benchmark.helpers.databases.FirebaseDatabaseImpl
import android.benchmark.helpers.databases.MockDatabaseImpl
import android.benchmark.helpers.dataservices.datasource.DataSourceContainerImpl
import android.benchmark.helpers.dataservices.datasource.MockUserDataSource
import android.benchmark.helpers.dataservices.datasource.MockVolunteersDataSource
import android.benchmark.log.Logger
import android.benchmark.ui.activities.main.fragments.FragmentChangerImpl
import android.benchmark.ui.fragments.KnownMappers
import android.benchmark.ui.fragments.genericlist.MapperInstanceProvider
import android.benchmark.ui.fragments.volunteer.details.VolunteerGenericItemMap

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