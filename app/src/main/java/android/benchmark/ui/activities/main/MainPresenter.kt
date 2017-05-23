package android.benchmark.ui.activities.main

import android.benchmark.ui.fragments.volunteer.list.VolunteerListFragment

internal class MainPresenter(val mainActivity: IMainActivity) :
        IMainPresenter {
    override fun onAuthenticationClick() = mainActivity.openAuthentication()

    override fun onSettingsClick() = mainActivity.openSettings()

    override fun onCreate() {
        val volunteerView = VolunteerListFragment()
        mainActivity.navigateTo(volunteerView)
    }
}

