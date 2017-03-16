package android.benchmark.ui.activities.main

import android.benchmark.ui.fragments.volunteer.VolunteerListFragment

internal class MainPresenter(val mainActivity: IMainActivity) :
        IMainPresenter {
    override fun onCreate() {
        //animationBenchmark.show()
        val volunteerView = VolunteerListFragment()
        mainActivity.navigateTo(volunteerView)
    }
}

interface IMainPresenter {
    fun onCreate()
}