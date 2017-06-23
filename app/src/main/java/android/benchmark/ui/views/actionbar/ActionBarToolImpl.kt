package android.benchmark.ui.views.actionbar

import android.benchmark.ui.activities.main.MainActivityImpl

internal class ActionBarToolImpl(val mainActivityImpl: MainActivityImpl) : ActionBarTool {
    override var onBackPressed = fun(): Boolean { return false }
    val actionBar by lazy { mainActivityImpl.supportActionBar }

    override fun clearOnBackPressed() {
        onBackPressed = fun() : Boolean { return false }
    }

    override fun backPressed(): Boolean = onBackPressed()

    override fun showBackArrow() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun hideBackArrow() {
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun setTitle(title: String) {
        actionBar?.title = title
    }
}