package android.benchmark

import android.app.Application
import android.benchmark.services.Services
import android.benchmark.services.ServicesImpl
import android.benchmark.services.authentication.FacebookAuthentication
import android.benchmark.services.cache.AndroidLocalDataCache
import android.benchmark.services.content.AndroidResourceManager
import android.benchmark.services.dataservices.DataServiceMock
import android.benchmark.ui.utils.AppVersionProviderImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Services.instance = ServicesImpl(
                dataService = DataServiceMock(AndroidResourceManager(this)),
                dataCache = AndroidLocalDataCache(baseContext),
                appVersionProvider = AppVersionProviderImpl(packageManager, packageName),
                facebookAuthentication = FacebookAuthentication())
    }
}

