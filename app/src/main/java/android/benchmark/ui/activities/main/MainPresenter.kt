package android.benchmark.ui.activities.main

import android.benchmark.ui.fragments.volunteer.VolunteerListFragment

internal class MainPresenter(val mainActivity: IMainActivity) :
        IMainPresenter {
    override fun onSettingsClick() {
        mainActivity.openSettings()
    }

    override fun onCreate() {
        val volunteerView = VolunteerListFragment()
        mainActivity.navigateTo(volunteerView)
    }
}

