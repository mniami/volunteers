package android.benchmark

import android.app.Application
import android.benchmark.auth.GoogleAuthImpl
import android.benchmark.helpers.Services
import android.benchmark.helpers.ServicesImpl
import android.benchmark.helpers.authentication.FacebookAuthentication
import android.benchmark.helpers.cache.AndroidLocalDataCache
import android.benchmark.helpers.content.AndroidResourceManager
import android.benchmark.helpers.dataservices.DataServiceMock
import android.benchmark.ui.utils.AppVersionProviderImpl

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Services.instance = ServicesImpl(
                dataService = DataServiceMock(AndroidResourceManager(this)),
                dataCache = AndroidLocalDataCache(baseContext),
                appVersionProvider = AppVersionProviderImpl(packageManager, packageName),
                facebookAuthentication = FacebookAuthentication(),
                googleAuth = GoogleAuthImpl())
    }
}