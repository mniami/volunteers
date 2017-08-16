package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.helpers.dataservices.datasource.VolunteersDataSource
import android.benchmark.ui.fragments.base.Presenter

class VolunteersPresenter(val dataSourceContainer: DataSourceContainer, var fragment: IVolunteerListFragment) : Presenter() {
    override fun onResume() {
        val dataSource = dataSourceContainer.getDataSource(VolunteersDataSource.ID) as VolunteersDataSource
        dataSource.data.observable.toList().doOnSuccess { all -> all
            fragment.showVolunteers(all)
        }
    }
}
