package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.helpers.dataservices.DataService
import android.benchmark.ui.fragments.base.Presenter

class VolunteersPresenter(val dataService: DataService, var fragment: IVolunteerListFragment) : Presenter() {
    override fun onResume() {
        dataService.getVolunteers().subscribe { data ->
            fragment.showVolunteers(data)
        }
    }
}
