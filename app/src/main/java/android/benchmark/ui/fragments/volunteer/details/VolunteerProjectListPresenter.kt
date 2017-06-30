package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.services.dataservices.DataService
import android.benchmark.ui.fragments.base.Presenter

interface IVolunteerProjectListFragment {
}
class VolunteerProjectListPresenter(val dataService: DataService, val view : IVolunteerProjectListFragment) : Presenter(){
    var volunteer = Volunteer()

    fun onViewCreated() {
    }
}