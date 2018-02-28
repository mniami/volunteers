package android.benchmark

import android.androidkotlinbenchmark.App
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
import android.benchmark.helpers.databases.MockDatabaseImpl
import android.benchmark.helpers.dataservices.datasource.*
import android.benchmark.ui.activities.main.FragmentChangerImpl
import android.benchmark.ui.fragments.KnownMappers
import android.benchmark.ui.fragments.genericlist.MapperInstanceProvider
import android.benchmark.ui.fragments.volunteer.details.VolunteerGenericItemMap
import android.benchmark.ui.utils.AppVersionProviderImpl

class DependencyModule {
    fun init(app : App){
        val resourceManager = AndroidResourceManager(app)
        val auth = AuthImpl(AuthUser.createEmpty())
        val database = FirebaseDatabaseImpl(auth)
        val mapperInstanceProvider = MapperInstanceProvider()
        val dataContainer = DataSourceContainerImpl()
        val fragmentChanger = FragmentChangerImpl(dataSourceContainer = dataContainer)

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