package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Project
import android.benchmark.ui.fragments.volunteer.details.project.ProjectDetailsFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class ProjectAdapter(fragmentManager: FragmentManager, val projectList: List<Project>) :
        FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        val f = ProjectDetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable("project", projectList[position])
        f.arguments = bundle
        return f
    }

    override fun getCount(): Int {
        return projectList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        val project = projectList[position]
        return project?.name
    }
}