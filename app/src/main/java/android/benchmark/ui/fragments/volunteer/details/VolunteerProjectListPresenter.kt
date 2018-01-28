package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BasicPresenter

class VolunteerProjectListPresenter(val view : IVolunteerProjectListFragment) : BasicPresenter(){
    var volunteer = Volunteer()

    fun onViewCreated() {
    }
}