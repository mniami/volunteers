package guideme.volunteers.ui.fragments.volunteer.details

import guideme.volunteers.R
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import guideme.volunteers.ui.fragments.volunteer.details.project.ProjectListAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.let { rv ->
            rv.setHasFixedSize(true)
            rv.layoutManager = LinearLayoutManager(context)
            presenter?.let {
                rv.adapter = ProjectListAdapter(it.volunteer.person.projects.map { pair -> pair.value }) { project ->
                    mainActivity.showProject(project)
                }
            }
        }
    }
}