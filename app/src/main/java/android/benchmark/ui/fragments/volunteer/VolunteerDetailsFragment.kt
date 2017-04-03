package android.benchmark.ui.fragments.volunteer

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.benchmark.ui.activities.main.IMainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation

class VolunteerDetailsFragment : Fragment(), IVolunteerDetailsFragment {
    private val presenter : VolunteerDetailsPresenter = VolunteerDetailsPresenter(this)
    private val mainActivity by lazy { activity as IMainActivity }
    private val actionBarTool by lazy { mainActivity.actionBarTool }
    private var volunteer : Volunteer? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (savedInstanceState == null) {
            return inflater!!.inflate(R.layout.volunteer_details_fragment, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        volunteer = args?.get("volunteer") as Volunteer
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter){
            volunteer?.name?.let { actionBarTool.setTitle(it) }
            actionBarTool.showBackArrow()
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}