package android.benchmark.ui.fragments.volunteer.details.admin

import android.androidkotlinbenchmark.R
import android.benchmark.domain.Person
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.volunteer.details.presenters.UserPresenter
import android.os.Bundle
import kotlinx.android.synthetic.main.admin_user_details.*
import android.widget.TabHost.TabSpec



class AdminUserDetailsFragment: BaseFragment<UserPresenter>() {
    companion object {
        val PERSON_ARG = "person"
    }
    init {
        presenter = UserPresenter()
        configuration = FragmentConfiguration.withLayout(R.layout.admin_user_details).showBackArrow().create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabHost?.let {
            it.setup()

            val tab1 = it.newTabSpec("Details")
            tab1.setContent(R.id.detailsLayout)
            tab1.setIndicator("Details")

            it.addTab(tab1)

            val tab2 = it.newTabSpec("Skills")
            tab2.setContent(R.id.skillsLayout)
            tab2.setIndicator("Skills")

            it.addTab(tab2)
        }
    }

    override fun onResume() {
        super.onResume()
        tabHost?.let {
            val tab1 = it.newTabSpec("First Tab")
            val tab2 = it.newTabSpec("Second Tab")
        }
    }
    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.let {
            it.person = args?.get(PERSON_ARG) as Person
        }
    }
}