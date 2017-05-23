package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.services.dataservices.IDataService

class VolunteersPresenter(val dataService: IDataService,
                          val fragment: IVolunteerListFragment) {
    fun onResume() {
        dataService.getVolunteers().subscribe { data ->
            fragment.showVolunteers(data)
        }
    }
}
