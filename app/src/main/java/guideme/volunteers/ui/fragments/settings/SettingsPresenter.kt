package guideme.volunteers.ui.fragments.settings

import guideme.volunteers.helpers.dataservices.datasource.DataSourceContainer
import guideme.volunteers.ui.fragments.base.BasicPresenter
import guideme.volunteers.ui.utils.AppVersionProvider

class SettingsPresenter(val appVersionProvider: AppVersionProvider,
                        val dataSourceContainer: DataSourceContainer) : BasicPresenter() {
    var view: ISettingsFragment? = null

    fun getAppVersion() : String {
        return appVersionProvider.getAppVersion()
    }

}