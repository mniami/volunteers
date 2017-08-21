package android.benchmark.ui.fragments.base

import android.benchmark.ui.activities.main.EmptyMainActivity
import android.benchmark.ui.activities.main.MainActivity
import android.benchmark.ui.views.actionbar.ActionBarTool
import android.benchmark.ui.views.actionbar.EmptyActionBarTool
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import java.io.Serializable

open class BaseFragment<T : IPresenter> : Fragment() {
    var presenter: T? = null
    var configuration: FragmentConfiguration = FragmentConfiguration()
    var mainActivity: MainActivity = EmptyMainActivity(EmptyActionBarTool())
    var actionBar: ActionBarTool = EmptyActionBarTool()
    var layoutInflater: LayoutInflater? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
            actionBar = mainActivity.actionBarTool
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutInflater = inflater
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

class FragmentConfiguration(val toolbar: ToolbarConfiguration = ToolbarConfiguration(),
                            var layoutResourceId: Int? = null){
    companion object {
        fun withLayout(layoutResourceId: Int): Builder {
            return Builder().withLayout(layoutResourceId)
        }
    }

    class Builder {
        private val configuration = FragmentConfiguration()
        fun withLayout(layoutResourceId: Int): Builder {
            configuration.layoutResourceId = layoutResourceId
            return this
        }

        fun title(titleResourceId: Int): Builder {
            configuration.toolbar.titleResourceId = titleResourceId
            return this
        }

        fun showBackArrow(): Builder {
            configuration.toolbar.showBackArrow = true
            return this
        }

        fun create(): FragmentConfiguration {
            return FragmentConfiguration(configuration.toolbar, configuration.layoutResourceId)
        }

        fun noArrow(): Builder {
            configuration.toolbar.showBackArrow = false
            return this
        }
    }
}

class ToolbarConfiguration(var titleResourceId: Int? = null, var showBackArrow: Boolean? = null) : Serializable