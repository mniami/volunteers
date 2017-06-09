package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.volunteer_details_fragment.*

class VolunteerDetailsFragment : BaseFragment<VolunteerDetailsPresenter>(), IVolunteerDetailsFragment {
    private val actionBarTool by lazy { mainActivity.actionBarTool }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuration = FragmentConfiguration.withLayout(R.layout.volunteer_details_fragment).create()
        presenter = VolunteerDetailsPresenter(this)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.let { v ->
            updateView()
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.volunteer = args?.get("volunteer") as Volunteer
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter) {
            presenter?.volunteer?.let { actionBarTool.setTitle("${it.name} ${it.surname}") }
            actionBarTool.showBackArrow()
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    private fun updateView() {
        presenter?.volunteer?.let { v ->
            tvSubHeader?.text = "${v.name} ${v.surname}"
            tvHeader?.text = v.volunteerType
            tvShortDescription?.text = v.shortDescription
            tvDescription?.text = v.description
            volunteerDetailsFragmentPager?.adapter = ProjectAdapter(fragmentManager, v.projects)

            Picasso.with(context).load(v.avatarImageUri).into(ivImage)
        }
    }
}