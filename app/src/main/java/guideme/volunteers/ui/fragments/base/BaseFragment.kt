package guideme.volunteers.ui.fragments.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.view.animation.Animation
import guideme.volunteers.log.createLog
import guideme.volunteers.ui.activities.main.EmptyMainActivity
import guideme.volunteers.ui.activities.main.MainActivity
import guideme.volunteers.ui.tools.ToolbarConfigurationHandler
import guideme.volunteers.ui.views.actionbar.ActionBarTool
import guideme.volunteers.ui.views.actionbar.EmptyActionBarTool

open class BaseFragment<T : Presenter> : Fragment() {
    protected val log = createLog(this)
    private val toolbarConfigurationHandler = ToolbarConfigurationHandler()
    protected var presenter: T? = null
    protected var configuration: FragmentConfiguration = FragmentConfiguration()
    protected lateinit var mainActivity: MainActivity
    protected lateinit var actionBar: ActionBarTool

    override fun onAttach(context: Context?) {
        log.d { "onAttach" }
        super.onAttach(context)
        if (context is MainActivity) {
            mainActivity = context
            actionBar = mainActivity.actionBarTool
        }
    }

    override fun onDetach() {
        log.d { "onDetach" }
        actionBar = EmptyActionBarTool()
        mainActivity = EmptyMainActivity()
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
            toolbarConfigurationHandler.applyConfiguration(mainActivity, configuration)
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        log.d { "onCreate" }
        super.onCreate(savedInstanceState)
        if (configuration.toolbar.menuResourceId != null) {
            setHasOptionsMenu(true)
        }
        presenter?.onCreate()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        val configurationMenu = configuration.toolbar.menuResourceId
        if (configurationMenu != null) {
            menu?.clear()
            inflater?.inflate(configurationMenu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onResume() {
        log.d { "onResume" }
        super.onResume()
        presenter?.onResume()
    }

    override fun onPause() {
        log.d { "onPause" }
        super.onPause()
        presenter?.onPause()
    }

    override fun onDestroy() {
        log.d { "onDestroy" }
        super.onDestroy()
        presenter?.onDestroy()
    }
}