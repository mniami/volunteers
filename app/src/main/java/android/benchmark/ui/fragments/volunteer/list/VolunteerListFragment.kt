package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.services.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.volunteer_list_fragment.*

class VolunteerListFragment : BaseFragment<VolunteersPresenter>(), IVolunteerListFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_list_fragment).title(R.string.app_name).noArrow().create()
        presenter = VolunteersPresenter(Services.dataService, this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
    }

    override fun showVolunteers(volunteers: List<Volunteer>) {
        recyclerView?.let {
            val adapter = ListAdapter(volunteers) { volunteer ->
                mainActivity?.showVolunteer(volunteer)
            }
            recyclerView?.adapter = adapter
        }
    }
}

