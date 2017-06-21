package android.benchmark.services

import android.benchmark.services.authentication.FacebookAuthentication
import android.benchmark.services.cache.EmptyLocalDataCache
import android.benchmark.services.cache.LocalDataCache
import android.benchmark.services.dataservices.DataService
import android.benchmark.services.dataservices.DataServiceMock
import android.benchmark.ui.utils.AppVersionProvider
import android.benchmark.ui.utils.EmptyAppVersionProvider

class Services(val dataService: DataService = DataServiceMock(),
               val facebookAuthentication: FacebookAuthentication = FacebookAuthentication(),
               val dataCache: LocalDataCache = EmptyLocalDataCache(),
               val appVersionProvider: AppVersionProvider = EmptyAppVersionProvider()) {
    companion object {
        var instance: Services = Services()
    }
}