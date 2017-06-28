package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.utils.safeLet
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import kotlinx.android.synthetic.main.volunteer_details_fragment.*

class VolunteerDetailsFragment : BaseFragment<VolunteerDetailsPresenter>(), IVolunteerDetailsFragment {
    init {
        presenter = VolunteerDetailsPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_fragment).showBackArrow().create()
    }

    override fun onVolunteerLoaded(volunteer: Volunteer) {
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateView()
    }

    override fun onResume() {
        super.onResume()
    }

    fun updateView(){
        viewPager?.adapter = object : FragmentPagerAdapter(fragmentManager) {
            override fun getCount(): Int {
                return 2
            }

            override fun getItem(position: Int): Fragment? {
                return presenter?.volunteer?.let { volunteer ->
                    var fragment: Fragment? = null
                    when (position) {
                        0 -> fragment = VolunteerDetailsAccountFragment()
                        1 -> fragment = VolunteerProjectListFragment()
                    }
                    val bundle = Bundle()
                    bundle.putSerializable("volunteer", volunteer)
                    fragment?.arguments = bundle
                    return@let fragment
                }
            }
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.volunteer = args?.get("volunteer") as Volunteer
    }
}