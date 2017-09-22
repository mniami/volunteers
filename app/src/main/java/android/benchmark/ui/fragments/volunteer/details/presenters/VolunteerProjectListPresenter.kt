package android.benchmark.ui.fragments.volunteer.details.presenters

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.Presenter
import android.benchmark.ui.fragments.volunteer.details.IVolunteerProjectListFragment

class VolunteerProjectListPresenter(val view : IVolunteerProjectListFragment) : Presenter(){
    var volunteer = Volunteer()

    fun onViewCreated() {
    }
}