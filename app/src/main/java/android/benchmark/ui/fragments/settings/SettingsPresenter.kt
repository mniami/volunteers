package android.benchmark.ui.fragments.settings

import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.ui.activities.main.MainActivity
import android.benchmark.ui.fragments.base.BasicPresenter
import android.benchmark.ui.utils.AppVersionProvider

class SettingsPresenter(val appVersionProvider: AppVersionProvider,
                        var mainView: MainActivity,
                        val dataSourceContainer: DataSourceContainer) : BasicPresenter() {
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