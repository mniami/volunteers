package android.benchmark.ui.fragments.base

open class BasicPresenter(protected var basicView: BasicView = EmptyBasicView()) : Presenter {
    override fun onResume() {//nothing to do
    }
    override fun onPause() {//nothing to do
    }
    override fun onDestroy() {//nothing to do
        basicView = EmptyBasicView()
    }
    override fun onCreate() {
        //nothing to do
    }
}