package android.benchmark.ui.utils

import android.app.Activity
import android.content.pm.PackageManager

interface AppVersionProvider {
    fun getAppVersion(): String
}

class EmptyAppVersionProvider : AppVersionProvider {
    override fun getAppVersion(): String = ""
}

class AppVersionProviderImpl(val packageManager: PackageManager, val packageName: String) : AppVersionProvider {
    override fun getAppVersion(): String {
        val pInfo = packageManager.getPackageInfo(packageName, 0)
        val version = pInfo.versionName
        return version
    }
    companion object {
        fun fromActivity(activity: Activity) : AppVersionProvider {
            return AppVersionProviderImpl(activity.packageManager, activity.packageName)
        }
    }
}