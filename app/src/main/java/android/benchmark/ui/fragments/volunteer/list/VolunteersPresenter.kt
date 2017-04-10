package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.services.IDataService
import android.benchmark.ui.fragments.volunteer.list.IVolunteerListFragment

class VolunteersPresenter(val dataService: IDataService,
                          val fragment: IVolunteerListFragment) {
    fun onResume() {
        dataService.getVolunteers().subscribe { data ->
            fragment.showVolunteers(data)
        }
    }
}
