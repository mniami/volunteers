package android.benchmark.ui.views.actionbar

class EmptyActionBarTool : ActionBarTool {
    override fun setTitle(title: String) {
    }

    override fun showBackArrow() {
    }

    override fun hideBackArrow() {
    }

    override var onBackPressed: () -> Boolean = { false }

    override fun clearOnBackPressed() {
    }

    override fun backPressed(): Boolean = false
}