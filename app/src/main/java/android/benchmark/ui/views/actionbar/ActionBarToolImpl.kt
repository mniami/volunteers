package android.benchmark.ui.views.actionbar

import android.benchmark.ui.activities.main.MainActivityImpl

internal class ActionBarToolImpl(val mainActivityImpl: MainActivityImpl) : ActionBarTool {
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

    override fun hideOptions() {
        changeOptionsVisibility(false)
    }

    override fun showOptions() {
        changeOptionsVisibility(true)
    }

    override var onBackPressed = fun(): Boolean { return false }

    private fun changeOptionsVisibility (visible : Boolean){
        //TODO("menu")
//        actionBar?.let {
//            val menu = mainActivityImpl.findViewById(R.menu.menu)
//            val favorite = menu.findViewById(R.id.action_favorite)
//            favorite?.let {
//                it.visibility = if (visible) View.VISIBLE else View.INVISIBLE
//            }
//        }
    }
}