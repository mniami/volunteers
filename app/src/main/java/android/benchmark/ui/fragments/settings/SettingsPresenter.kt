package android.benchmark.ui.fragments.settings

import android.benchmark.services.dataservices.IDataService
import android.benchmark.ui.activities.main.IMainActivity
import android.benchmark.ui.utils.AppVersionProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SettingsPresenter(val view: ISettingsFragment, val appVersionProvider: AppVersionProvider,
                        val dataService: IDataService,
                        val mainView: IMainActivity) {
    var userRef: Disposable? = null

    fun onResume() {
        mainView.actionBarTool.onBackPressed = {
            mainView.goBack()
            true
        }
        view.setAppVersion(appVersionProvider.getAppVersion())
        userRef = Observable.wrap(dataService.getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { user ->
                    view.showUserData(user)
                }
    }

    fun onPause() {
        mainView.actionBarTool.clearOnBackPressed()
        userRef?.let {
            it.dispose()
            userRef = null
        }
    }
}