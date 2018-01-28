package android.benchmark.ui.fragments.base

import android.benchmark.ui.activities.main.EmptyMainActivity
import android.benchmark.ui.activities.main.MainActivity
import android.benchmark.ui.activities.main.MainActivityListener
import android.benchmark.ui.views.actionbar.ActionBarTool
import android.benchmark.ui.views.actionbar.EmptyActionBarTool
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation

open class BaseFragment<T : Presenter> : Fragment() {
    private val emptyMainActivity = EmptyMainActivity(EmptyActionBarTool())
    protected var presenter: T? = null
    protected var configuration: FragmentConfiguration = FragmentConfiguration()
    protected var mainActivity: MainActivity = emptyMainActivity
    protected var actionBar: ActionBarTool = EmptyActionBarTool()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MainActivity) {
            mainActivity = context
            actionBar = mainActivity.actionBarTool
        }
    }

    override fun onDetach() {
        mainActivity = emptyMainActivity
        super.onDetach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        configuration.layoutResourceId?.let {
            return inflater.inflate(it, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        if (enter) {
            configuration.toolbar.titleResourceId?.let {
                mainActivity.actionBarTool.setTitle(mainActivity.getResourceText(it))
            }
            configuration.toolbar.showBackArrow?.let { showBackArrow ->
                if (showBackArrow) {
                    mainActivity.actionBarTool.showBackArrow()
                } else {
                    mainActivity.actionBarTool.hideBackArrow()
                }
            }
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter?.onCreate()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }
}