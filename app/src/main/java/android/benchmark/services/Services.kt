package android.benchmark.services

import android.benchmark.services.authentication.FacebookAuthentication
import android.benchmark.services.cache.EmptyLocalDataCache
import android.benchmark.services.cache.LocalDataCache
import android.benchmark.services.dataservices.DataService
import android.benchmark.services.dataservices.EmptyDataService
import android.benchmark.ui.utils.AppVersionProvider
import android.benchmark.ui.utils.EmptyAppVersionProvider

interface IServices {
    val dataService: DataService
    val facebookAuthentication: FacebookAuthentication
    val dataCache: LocalDataCache
    val appVersionProvider: AppVersionProvider
}

class EmptyServices : IServices {
    override val dataService: DataService = EmptyDataService()
    override val facebookAuthentication: FacebookAuthentication = FacebookAuthentication()
    override val dataCache: LocalDataCache = EmptyLocalDataCache()
    override val appVersionProvider: AppVersionProvider = EmptyAppVersionProvider()
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
        override val appVersionProvider: AppVersionProvider) : IServices {
}