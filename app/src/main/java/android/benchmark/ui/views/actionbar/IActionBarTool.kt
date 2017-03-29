package android.benchmark.ui.views.actionbar

interface IActionBarTool {
    fun setTitle(title : String)
    fun showBackArrow()
    fun hideBackArrow()
    var onBackPressed : () -> Boolean
    fun clearOnBackPressed()
    fun backPressed() : Boolean
}