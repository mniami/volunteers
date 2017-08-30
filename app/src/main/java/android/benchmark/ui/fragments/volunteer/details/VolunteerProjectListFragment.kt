package android.benchmark.ui.fragments.volunteer.details

import android.androidkotlinbenchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.volunteer.details.project.ProjectListAdapter
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.volunteer_details_projects.*

class VolunteerProjectListFragment : BaseFragment<VolunteerProjectListPresenter>(), IVolunteerProjectListFragment {

    init {
        presenter = VolunteerProjectListPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_projects).showBackArrow().create()
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.volunteer = args?.get("volunteer") as Volunteer
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.let { rv ->
            rv.setHasFixedSize(true)
            rv.layoutManager = LinearLayoutManager(context)
            presenter?.let {
                rv.adapter = ProjectListAdapter(it.volunteer.projects) { project ->
                    mainActivity.showProject(project)
                }
            }
        }
    }
}