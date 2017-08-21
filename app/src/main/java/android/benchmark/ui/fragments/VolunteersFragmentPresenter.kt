package android.benchmark.ui.fragments

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.Services
import android.benchmark.helpers.android.fromSerializable
import android.benchmark.helpers.android.withStringValue
import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.helpers.dataservices.datasource.VolunteersDataSource
import android.benchmark.ui.fragments.base.ToolbarConfiguration
import android.benchmark.ui.fragments.genericlist.GenericItemClickEvent
import android.benchmark.ui.fragments.genericlist.GenericListFragment
import android.benchmark.ui.fragments.genericlist.GenericListFragmentImpl
import android.benchmark.ui.fragments.volunteer.details.VolunteerGenericItemMap
import android.os.Bundle
import android.support.v4.app.Fragment

class VolunteersFragmentPresenter {
    fun createFragment(dataSourceContainer : DataSourceContainer, onClick : (Volunteer) -> Unit) : Fragment {
        val dataSource = dataSourceContainer.getDataSource(VolunteersDataSource.ID)
        var bundle = Bundle()
                .fromSerializable(GenericListFragment.TOOLBAR_CONFIGURATION, ToolbarConfiguration(titleResourceId = R.string.volunteers_title, showBackArrow = false))
                .withStringValue(GenericListFragment.EVENT_CLICK_ID, VolunteersDataSource.ID.key)
                .withStringValue(GenericListFragment.MAPPER_CLASS_NAME, VolunteerGenericItemMap::class.java.name)
        if (dataSource != null){
            bundle = bundle.fromSerializable(GenericListFragment.DATA_SOURCE_ID, dataSource.id)
        }

        val fragment = GenericListFragmentImpl()
        Services.instance.eventBusContainer.get<GenericItemClickEvent>(VolunteersDataSource.ID.key).observe().subscribe { it ->
            val volunteer = it.item.data as Volunteer?
            if (volunteer != null) {
                onClick(volunteer)
            }
        }
        fragment.arguments = bundle
        return fragment
    }
}