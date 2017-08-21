package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.volunteer_details_fragment.*

class VolunteerDetailsFragment : BaseFragment<VolunteerDetailsPresenter>(), IVolunteerDetailsFragment {
    companion object {
        val VOLUNTEER_ARG = "volunteer"
    }
    init {
        presenter = VolunteerDetailsPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_fragment).showBackArrow().create()
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    fun updateView() {
        actionBar.hideOptions()
        presenter?.volunteer?.let { v ->
            actionBar.setTitle("Volunteers Details")
            tvSubHeader?.text = "${v.name} ${v.surname}"
            tvHeader?.text = v.volunteerType
            Picasso.with(context).load(v.avatarImageUri).into(ivImage)
            tvShortDescription?.text = v.shortDescription
        }
        viewPager?.let {
            it.adapter = object : FragmentPagerAdapter(childFragmentManager) {
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

                override fun getPageTitle(position: Int): CharSequence {
                    when (position) {
                        0 -> return "Info"
                        1 -> return "Projects"
                    }
                    return ""
                }
            }
            slidingTabLayout?.setViewPager(it)
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.let {
            it.volunteer = args?.get(VOLUNTEER_ARG) as Volunteer
        }
    }
}