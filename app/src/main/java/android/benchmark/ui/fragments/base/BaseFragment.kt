package android.benchmark.ui.fragments.base

import android.benchmark.R
import android.benchmark.ui.activities.main.IMainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation

open class BaseFragment<T>(
        val layoutResourceId: Int,
        val toolbarConfiguration : ToolbarConfiguration,
        val presenter : T) : Fragment() {
    val mainActivity by lazy { activity as IMainActivity }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter){
            mainActivity.actionBarTool.setTitle(mainActivity.getResourceText(toolbarConfiguration.titleResourceId))
            if (toolbarConfiguration.showBackArrow) {
                mainActivity.actionBarTool.showBackArrow()
            }
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}

class ToolbarConfiguration (val titleResourceId : Int, val showBackArrow : Boolean)
