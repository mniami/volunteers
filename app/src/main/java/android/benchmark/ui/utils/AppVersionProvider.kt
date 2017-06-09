package android.benchmark.ui.utils

import android.app.Activity
import android.content.pm.PackageManager

class AppVersionProvider (val packageManager: PackageManager, val packageName: String){
    fun getAppVersion(): String {
        val pInfo = packageManager.getPackageInfo(packageName, 0)
        val version = pInfo.versionName
        return version
    }
    companion object {
        fun fromActivity(activity: Activity) : AppVersionProvider {
            return AppVersionProvider(activity.packageManager, activity.packageName)
        }
    }
}