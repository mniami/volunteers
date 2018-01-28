package android.benchmark.ui.activities.main.base

import android.benchmark.ui.activities.main.MainActivityListener
import android.benchmark.ui.views.actionbar.ActionBarTool

interface BaseMainActivity {
    val actionBarTool: ActionBarTool
    fun addListener(listener : MainActivityListener)
    fun removeListener(listener : MainActivityListener)
    fun goBack()
    fun openHome()
}