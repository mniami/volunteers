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

    override fun onResume() {
        super.onResume()
        mainView.actionBarTool.onBackPressed = {
            mainView.goBack()
            true
        }
        view?.setAppVersion(appVersionProvider.getAppVersion())
    }

    override fun onPause() {
        super.onPause()
        mainView.actionBarTool.clearOnBackPressed()
    }
}