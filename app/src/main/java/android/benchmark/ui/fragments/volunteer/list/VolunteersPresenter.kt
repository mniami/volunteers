package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.services.dataservices.IDataService
import android.benchmark.ui.fragments.base.Presenter

class VolunteersPresenter(val dataService: IDataService, var fragment: IVolunteerListFragment) : Presenter() {
    override fun onResume() {
        dataService.getVolunteers().subscribe { data ->
            fragment.showVolunteers(data)
        }
    }
}
