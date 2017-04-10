package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.services.DataServiceMock
import android.benchmark.services.IDataService
import android.benchmark.ui.activities.main.IMainActivity
import android.benchmark.ui.fragments.volunteer.list.VolunteersPresenter
import android.benchmark.ui.fragments.volunteer.list.IVolunteerListFragment
import android.benchmark.ui.fragments.volunteer.list.ListAdapter
import android.benchmark.ui.views.actionbar.IActionBarTool
import android.content.Context
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
    private val dataService: IDataService by lazy { DataServiceMock() }
    private val presenter: VolunteersPresenter by lazy { VolunteersPresenter(dataService, this) }
    private var mainActivity : IMainActivity? = null
    private var recyclerView : RecyclerView? = null
    private var actionBarTool : IActionBarTool? = null

    override fun getName(): String {
        return FRAGMENT_NAME
    }

    override fun getFragment(): Fragment {
        return this
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mainActivity = context as IMainActivity
        actionBarTool = mainActivity?.actionBarTool
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (null == savedInstanceState) {
            return inflater!!.inflate(R.layout.volunteer_list_fragment, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view?.findViewById(R.id.recycler_view) as RecyclerView
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

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter){
            mainActivity?.let { ma ->
                actionBarTool?.setTitle(ma.getResourceText(R.string.app_name))
                actionBarTool?.hideBackArrow()
            }
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}

