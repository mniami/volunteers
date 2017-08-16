package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.Presenter

class VolunteerProjectListPresenter(val view : IVolunteerProjectListFragment) : Presenter(){
    var volunteer = Volunteer()

    fun onViewCreated() {
    }
}