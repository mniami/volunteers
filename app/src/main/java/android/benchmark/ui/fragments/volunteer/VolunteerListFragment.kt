package android.benchmark.ui.fragments.volunteer

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.services.DataServiceMock
import android.benchmark.services.IDataService
import android.benchmark.ui.activities.main.IMainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation

class VolunteerListFragment : Fragment(), IVolunteerListFragment {
    private val FRAGMENT_NAME = "VolunteerList"
    private val mainActivity by lazy { activity as IMainActivity }
    private val dataService: IDataService by lazy { DataServiceMock() }
    private val presenter: VolunteersPresenter by lazy { VolunteersPresenter(dataService, this) }
    private val recyclerView by lazy { view?.findViewById(R.id.recycler_view) as RecyclerView }

    override fun getName(): String {
        return FRAGMENT_NAME
    }

    override fun getFragment(): Fragment {
        return this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.volunteer_list_fragment, container, false)
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
        if (recyclerView.adapter == null) {
            val adapter = ListAdapter(volunteers)
            recyclerView.adapter = adapter
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter){
            mainActivity.actionBarTool.setTitle(mainActivity.getResourceText(R.string.app_name))
            mainActivity.actionBarTool.hideBackArrow()
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}

