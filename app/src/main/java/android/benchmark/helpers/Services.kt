package android.benchmark.helpers

import android.benchmark.auth.GoogleAuthImpl
import android.benchmark.helpers.authentication.FacebookAuthentication
import android.benchmark.helpers.cache.EmptyLocalDataCache
import android.benchmark.helpers.cache.LocalDataCache
import android.benchmark.helpers.dataservices.DataService
import android.benchmark.helpers.dataservices.EmptyDataService
import android.benchmark.ui.utils.AppVersionProvider
import android.benchmark.ui.utils.EmptyAppVersionProvider

interface IServices {
    val dataService: DataService
    val facebookAuthentication: FacebookAuthentication
    val dataCache: LocalDataCache
    val appVersionProvider: AppVersionProvider
    val googleAuth : GoogleAuthImpl
}

class EmptyServices : IServices {
    override val dataService: DataService = EmptyDataService()
    override val facebookAuthentication: FacebookAuthentication = FacebookAuthentication()
    override val dataCache: LocalDataCache = EmptyLocalDataCache()
    override val appVersionProvider: AppVersionProvider = EmptyAppVersionProvider()
    override val googleAuth: GoogleAuthImpl = GoogleAuthImpl()
}

class Services {
    companion object {
        var instance: IServices = EmptyServices()
    }
}

class ServicesImpl(
        override val dataService: DataService,
        override val facebookAuthentication: FacebookAuthentication,
        override val dataCache: LocalDataCache,
        override val appVersionProvider: AppVersionProvider,
        override val googleAuth: GoogleAuthImpl) : IServices {
}