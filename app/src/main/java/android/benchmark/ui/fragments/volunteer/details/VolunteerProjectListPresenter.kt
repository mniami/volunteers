package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.services.dataservices.DataService
import android.benchmark.ui.fragments.base.Presenter

interface IVolunteerProjectListFragment {
    fun onProjectsLoaded(projects : List<Project>)
}
class VolunteerProjectListPresenter(val dataService: DataService, val view : IVolunteerProjectListFragment) : Presenter(){
    var volunteer = Volunteer()
        set(value) {
            field = value
            view.onProjectsLoaded(value.projects)
        }

    fun onViewCreated() {
    }
}