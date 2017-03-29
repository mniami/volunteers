package android.benchmark.ui.views.actionbar

import android.benchmark.ui.activities.main.MainActivity

internal class ActionBarTool(val mainActivity: MainActivity) : IActionBarTool {
    override var onBackPressed = fun(): Boolean { return false }
    val actionBar by lazy { mainActivity.supportActionBar }

    override fun clearOnBackPressed() {
        onBackPressed = fun() : Boolean { return false }
    }

    override fun backPressed() : Boolean{
        return this::onBackPressed.get().invoke()
    }

    override fun showBackArrow() {
        actionBar?.setDisplayHomeAsUpEnabled(true);
        actionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun hideBackArrow() {
        actionBar?.setDisplayHomeAsUpEnabled(false);
        actionBar?.setDisplayShowHomeEnabled(false);
    }

    override fun setTitle(title: String) {
        actionBar?.setTitle(title)
    }
}