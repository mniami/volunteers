package guideme.volunteers.ui.fragments.volunteer.details.users

import guideme.volunteers.R
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import kotlinx.android.synthetic.main.volunteer_details_account.*

class VolunteerDetailsAccountFragment : BaseFragment<VolunteerDetailsPresenter>(), IVolunteerDetailsFragment {
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
            tvDescription?.text = v.person.description
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.volunteer = args?.get("volunteer") as Volunteer
    }
}