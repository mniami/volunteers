package android.benchmark.ui.fragments.base

interface IPresenter {
    fun onCreate()
    fun onResume()
    fun onPause()
    fun onDestroy()
}
open class Presenter : IPresenter{
    override fun onResume() {//nothing to do
    }
    override fun onPause() {//nothing to do
    }
    override fun onDestroy() {//nothing to do
    }
    override fun onCreate() {
        //nothing to do
    }
}