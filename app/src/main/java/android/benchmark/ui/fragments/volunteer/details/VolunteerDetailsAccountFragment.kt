package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.volunteer_details_account.*

class VolunteerDetailsAccountFragment : BaseFragment<VolunteerDetailsPresenter>(), IVolunteerDetailsFragment{
    init {
        presenter = VolunteerDetailsPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_account).showBackArrow().create()
    }

    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun updateView() {
        presenter?.volunteer?.let { v ->
            tvDescription?.text = v.description
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.volunteer = args?.get("volunteer") as Volunteer
    }
}