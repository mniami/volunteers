package android.benchmark.ui.activities.main

import android.benchmark.services.dataservices.DataService

internal class MainPresenter(val mainActivity: MainActivity, val dataService: DataService) :
        IMainPresenter {
    override fun onAuthenticationClick() = mainActivity.openAuthentication()

    override fun onSettingsClick() = mainActivity.openSettings()

    override fun onCreate() {
        mainActivity.showVolunteerList()
    }
}

