package android.benchmark.ui.fragments.volunteer

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.activities.main.IMainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class VolunteerDetailsFragment : Fragment(), IVolunteerDetailsFragment {
    private val presenter : VolunteerDetailsPresenter = VolunteerDetailsPresenter(this)
    private val mainActivity by lazy { activity as IMainActivity }
    private val actionBarTool by lazy { mainActivity.actionBarTool }
    private var tvTitle : TextView? = null
    private var tvHeader : TextView? = null
    private var tvShortDescription : TextView? = null
    private var tvDescription : TextView? = null
    private var imImage : ImageView? = null
    private var vpViewPager : ViewPager? = null

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
            tvTitle = v.findViewById(R.id.tv_title) as TextView
            tvHeader = v.findViewById(R.id.tv_header) as TextView
            tvShortDescription = v.findViewById(R.id.tv_short_description) as TextView
            tvDescription = v.findViewById(R.id.tv_description) as TextView
            imImage = v.findViewById(R.id.iv_image) as ImageView
            vpViewPager = v.findViewById(R.id.volunteer_details_fragment_pager) as ViewPager
        }
        updateView()
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter.volunteer = args?.get("volunteer") as Volunteer
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter){
            presenter.volunteer?.name?.let { actionBarTool.setTitle(it) }
            actionBarTool.showBackArrow()
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    private fun updateView() {
        presenter.volunteer?.let { v ->
            tvTitle?.setText("${v.name} ${v.surname}")
            tvHeader?.setText(v.volunteerType)
            tvShortDescription?.setText(v.shortDescription)
            tvDescription?.setText(v.description)
            Picasso.with(context).load(v.avatarImageUri).into(imImage)
        }
    }
}