package android.benchmark.ui.fragments.volunteer.details.project

import android.benchmark.R
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.genericlist.GenericListAdapter
import android.benchmark.ui.fragments.volunteer.details.VolunteerGenericItemMap
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import kotlinx.android.synthetic.main.project_details_volunteers_fragment.*

class ProjectDetailsVolunteersFragment : BaseFragment<ProjectDetailsPresenter>(), IProjectDetailsFragment {
    init {
        presenter = ProjectDetailsPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.project_details_volunteers_fragment).showBackArrow().create()
    }
    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.project = args?.get("project") as Project
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView?.let { rv ->
            rv.setHasFixedSize(true)
            rv.layoutManager = LinearLayoutManager(context)
            presenter?.project?.let { project ->
                val obs = Observable.create { emitter: ObservableEmitter<Volunteer> ->
                    for (volunteer in project.volunteersInvolved){
                        emitter.onNext(volunteer)
                    }
                    emitter.onComplete()
                }
                val genericItemsObs = VolunteerGenericItemMap().map(obs)
                if (genericItemsObs != null) {
                    val genericItems = genericItemsObs.toList().blockingGet()
                    rv.adapter = GenericListAdapter(genericItems) { genericItem ->
                        //todo handle volunteer clicked
                    }
                }
            }
        }
    }
}