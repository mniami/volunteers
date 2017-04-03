package android.benchmark.ui.fragments.settings

import android.benchmark.ui.activities.main.IMainActivity
import android.benchmark.ui.utils.AppVersionProvider

class SettingsPresenter(val view: ISettingsFragment, val appVersionProvider: AppVersionProvider,
                        val mainView: IMainActivity) {
    fun onResume() {
        mainView.actionBarTool.onBackPressed = {
            mainView.goBack()
            true
        }
        view.setAppVersion(appVersionProvider.getAppVersion())
    }

    fun onPause() {
        mainView.actionBarTool.clearOnBackPressed()
    }
}