package guideme.volunteers.ui.fragments.base

import guideme.volunteers.ui.activities.main.EmptyMainActivity
import guideme.volunteers.ui.activities.main.MainActivity
import guideme.volunteers.ui.views.actionbar.ActionBarTool
import guideme.volunteers.ui.views.actionbar.EmptyActionBarTool
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.view.animation.Animation
import guideme.volunteers.R
import guideme.volunteers.helpers.Container
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.helpers.dataservices.errors.ErrorType
import guideme.volunteers.ui.tools.ToolbarConfigurationHandler
import io.reactivex.rxkotlin.subscribeBy

open class BaseFragment<T : Presenter> : Fragment() {
    private val emptyMainActivity = EmptyMainActivity(EmptyActionBarTool())
    private val toolbarConfigurationHandler = ToolbarConfigurationHandler()
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
            toolbarConfigurationHandler.applyConfiguration(mainActivity, configuration)
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (configuration.toolbar.menuResourceId != null){
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