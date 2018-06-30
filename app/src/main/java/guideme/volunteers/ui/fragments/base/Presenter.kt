package guideme.volunteers.ui.fragments.base

interface Presenter {
    fun onCreate()
    fun onResume()
    fun onPause()
    fun onDestroy()
}
