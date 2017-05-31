package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.activities.main.IMainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.volunteer_details_fragment.*

class VolunteerDetailsFragment : Fragment(), IVolunteerDetailsFragment {
    private val presenter: VolunteerDetailsPresenter = VolunteerDetailsPresenter(this)
    private val mainActivity by lazy { activity as IMainActivity }
    private val actionBarTool by lazy { mainActivity.actionBarTool }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (savedInstanceState == null) {
            return inflater!!.inflate(R.layout.volunteer_details_fragment, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.let { v ->
            updateView()
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter.volunteer = args?.get("volunteer") as Volunteer
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter) {
            presenter.volunteer?.let { actionBarTool.setTitle("${it.name} ${it.surname}") }
            actionBarTool.showBackArrow()
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    private fun updateView() {
        presenter.volunteer?.let { v ->
            tvSubHeader?.setText("${v.name} ${v.surname}")
            tvHeader?.setText(v.volunteerType)
            tvShortDescription?.setText(v.shortDescription)
            tvDescription?.setText(v.description)
            volunteerDetailsFragmentPager?.adapter = ProjectAdapter(fragmentManager, v.projects)

            Picasso.with(context).load(v.avatarImageUri).into(ivImage)
        }
    }
}