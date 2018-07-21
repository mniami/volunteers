package guideme.volunteers.ui.fragments.settings

import guideme.volunteers.helpers.dataservices.datasource.DataSourceContainer
import guideme.volunteers.ui.activities.main.MainActivity
import guideme.volunteers.ui.fragments.base.BasicPresenter
import guideme.volunteers.ui.utils.AppVersionProvider

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
    }

    fun getAppVersion() : String {
        return appVersionProvider.getAppVersion()
    }

    override fun onPause() {
        super.onPause()
        mainView.actionBarTool.clearOnBackPressed()
    }
}