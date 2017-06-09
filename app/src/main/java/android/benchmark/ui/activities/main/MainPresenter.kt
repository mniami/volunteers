package android.benchmark.ui.activities.main

import android.benchmark.services.dataservices.IDataService

internal class MainPresenter(val mainActivity: IMainActivity, val dataService: IDataService) :
        IMainPresenter {
    override fun onAuthenticationClick() = mainActivity.openAuthentication()

    override fun onSettingsClick() = mainActivity.openSettings()

    override fun onCreate() {
        mainActivity.showVolunteerList()
    }
}

