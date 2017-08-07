package android.benchmark.ui.fragments.volunteer.details.project

import android.benchmark.R
import android.benchmark.domain.Project
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import kotlinx.android.synthetic.main.project_details_information_fragment.*

class ProjectDetailsInfoFragment : BaseFragment<ProjectDetailsPresenter>(), IProjectDetailsFragment {
    init {
        presenter = ProjectDetailsPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.project_details_information_fragment).showBackArrow().create()
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.project = args?.get("project") as Project
    }

    override fun onResume() {
        super.onResume()

        presenter?.project?.let {
            webView?.loadData(it.longDescription, "text/html; charset=utf-8", "utf-8")
        }
    }
}