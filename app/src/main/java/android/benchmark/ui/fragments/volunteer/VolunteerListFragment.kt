package android.benchmark.ui.fragments.volunteer

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.services.DataServiceMock
import android.benchmark.services.IDataService
import android.benchmark.ui.fragments.base.IFragmentContainer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class VolunteerListFragment : Fragment(), IVolunteerListFragment {
    val dataService: IDataService by lazy { DataServiceMock() }
    val presenter: VolunteersPresenter
            by lazy { VolunteersPresenter(dataService, this) }

    override fun getFragment(): Fragment {
        return this
    }

    val recyclerView by lazy {
        view?.findViewById(R.id.recycler_view) as
                RecyclerView
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.volunteer_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    override fun showVolunteers(volunteers: List<Volunteer>) {
        val adapter = ListAdapter(volunteers)
        recyclerView.adapter = adapter
    }
}

interface IVolunteerListFragment : IFragmentContainer {
    fun showVolunteers(volunteers: List<Volunteer>)
}