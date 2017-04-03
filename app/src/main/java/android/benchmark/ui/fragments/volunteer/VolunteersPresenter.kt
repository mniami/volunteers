package android.benchmark.ui.fragments.volunteer

import android.benchmark.services.IDataService

class VolunteersPresenter(val dataService: IDataService,
                          val fragment: IVolunteerListFragment) {
    fun onResume() {
        dataService.getVolunteers().subscribe { data ->
            fragment.showVolunteers(data)
        }
    }
}
