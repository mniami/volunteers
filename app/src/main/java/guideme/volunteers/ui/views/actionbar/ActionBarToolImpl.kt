package guideme.volunteers.ui.views.actionbar

import guideme.volunteers.ui.activities.main.base.BaseMainActivityImpl
import guideme.volunteers.ui.utils.HtmlUtils

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
        mainActivityImpl?.supportActionBar?.title = HtmlUtils.fromHtml(title)
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