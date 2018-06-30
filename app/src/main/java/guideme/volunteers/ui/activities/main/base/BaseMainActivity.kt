package guideme.volunteers.ui.activities.main.base

import guideme.volunteers.ui.activities.main.MainActivityListener
import guideme.volunteers.ui.views.actionbar.ActionBarTool

interface BaseMainActivity {
    val actionBarTool: ActionBarTool
    fun addListener(listener : MainActivityListener)
    fun removeListener(listener : MainActivityListener)
    fun goBack()
    fun openHome()
}