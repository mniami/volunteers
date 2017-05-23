package android.benchmark.ui.fragments.settings

import android.benchmark.domain.User

interface ISettingsFragment {
    fun setAppVersion(appVersion : String)
    fun showUserData(user: User?)
}