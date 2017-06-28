package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.R
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.services.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.volunteer_details_projects.*

class VolunteerProjectListFragment : BaseFragment<VolunteerProjectListPresenter>(), IVolunteerProjectListFragment{

    init {
        presenter = VolunteerProjectListPresenter(Services.instance.dataService, this)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_projects).showBackArrow().create()
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.volunteer = args?.get("volunteer") as Volunteer
    }

    override fun onProjectsLoaded(projects: List<Project>) {
        recyclerView?.let {
            val layoutManager = LinearLayoutManager(context)
            it.layoutManager = layoutManager
            it.setHasFixedSize(true)
            it.adapter = ProjectAdapter(projects) {
                // on project clicked
            }
        }
    }
}