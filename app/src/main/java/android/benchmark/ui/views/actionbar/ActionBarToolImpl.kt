package android.benchmark.ui.views.actionbar

import android.benchmark.ui.activities.main.MainActivityImpl

internal class ActionBarToolImpl(var mainActivityImpl: MainActivityImpl?) : ActionBarTool {

    override fun clearOnBackPressed() {
        onBackPressed = fun() : Boolean { return false }
    }

    override fun backPressed(): Boolean = onBackPressed()

    override fun showBackArrow() {
        mainActivityImpl?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mainActivityImpl?.supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun hideBackArrow() {
        mainActivityImpl?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mainActivityImpl?.supportActionBar?.setDisplayShowHomeEnabled(false)
    }

    override fun setTitle(title: String) {
        mainActivityImpl?.supportActionBar?.title = title
    }

    override fun hideOptions() {
    }

    override fun showOptions() {
    }

    override var onBackPressed = fun(): Boolean { return false }

    override fun dispose() {
        mainActivityImpl = null
    }
}