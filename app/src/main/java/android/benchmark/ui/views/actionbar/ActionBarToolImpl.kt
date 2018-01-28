package android.benchmark.ui.views.actionbar

import android.benchmark.ui.activities.main.base.BaseMainActivityImpl
import android.text.Html
import android.text.Html.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE
import android.text.SpannedString

internal class ActionBarToolImpl(var mainActivityImpl: BaseMainActivityImpl?) : ActionBarTool {

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
        mainActivityImpl?.supportActionBar?.title = Html.fromHtml(title)
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