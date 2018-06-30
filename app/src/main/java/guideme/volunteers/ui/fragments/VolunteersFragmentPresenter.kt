package guideme.volunteers.ui.fragments

import guideme.volunteers.R
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.Services
import guideme.volunteers.helpers.android.fromSerializable
import guideme.volunteers.helpers.android.withStringValue
import guideme.volunteers.helpers.dataservices.datasource.DataSourceContainer
import guideme.volunteers.helpers.dataservices.datasource.VolunteerDataSource
import guideme.volunteers.ui.fragments.base.ToolbarConfiguration
import guideme.volunteers.ui.fragments.genericlist.GenericItemClickEvent
import guideme.volunteers.ui.fragments.genericlist.GenericListFragment
import guideme.volunteers.ui.fragments.genericlist.GenericListFragmentImpl
import android.os.Bundle
import android.support.v4.app.Fragment

class VolunteersFragmentPresenter {
    fun createFragment(dataSourceContainer : DataSourceContainer, onClick : (Volunteer) -> Unit) : Fragment {
        val dataSource = dataSourceContainer.getDataSource(VolunteerDataSource.ID)
        var bundle = Bundle()
                .fromSerializable(GenericListFragment.TOOLBAR_CONFIGURATION, ToolbarConfiguration(titleResourceId = R.string.volunteers_title, showBackArrow = false))
                .withStringValue(GenericListFragment.EVENT_CLICK_ID, VolunteerDataSource.ID.key)
                .withStringValue(GenericListFragment.MAPPER_CLASS_NAME, KnownMappers.volunteers)
        if (dataSource != null){
            bundle = bundle.fromSerializable(GenericListFragment.DATA_SOURCE_ID, dataSource.id)
        }

        val fragment = GenericListFragmentImpl()
        Services.instance.eventBusContainer.get<GenericItemClickEvent>(VolunteerDataSource.ID.key).observe().subscribe { it ->
            val volunteer = it.item.data as Volunteer?
            if (volunteer != null) {
                onClick(volunteer)
            }
        }
        fragment.arguments = bundle
        return fragment
    }
}