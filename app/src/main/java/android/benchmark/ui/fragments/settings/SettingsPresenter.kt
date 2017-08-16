package android.benchmark.ui.fragments.settings

import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.helpers.dataservices.datasource.UserDataSource
import android.benchmark.ui.activities.main.MainActivity
import android.benchmark.ui.fragments.base.Presenter
import android.benchmark.ui.utils.AppVersionProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SettingsPresenter(val appVersionProvider: AppVersionProvider,
                        var mainView: MainActivity,
                        val dataSourceContainer: DataSourceContainer) : Presenter() {
    var view: ISettingsFragment? = null
    var userRef: Disposable? = null

    override fun onResume() {
        super.onResume()
        mainView.actionBarTool.onBackPressed = {
            mainView.goBack()
            true
        }
        val userDataSource = dataSourceContainer.getDataSource(UserDataSource.ID) as UserDataSource
        view?.setAppVersion(appVersionProvider.getAppVersion())
        userRef = Observable.wrap(userDataSource.data.observable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user ->
                    view?.showUserData(user)
                }
    }

    override fun onPause() {
        super.onPause()
        mainView.actionBarTool.clearOnBackPressed()
        userRef?.let {
            it.dispose()
            userRef = null
        }
    }
}