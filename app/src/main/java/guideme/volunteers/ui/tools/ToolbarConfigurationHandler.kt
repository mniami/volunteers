package guideme.volunteers.ui.tools

import guideme.volunteers.ui.activities.main.MainActivity
import guideme.volunteers.ui.fragments.base.FragmentConfiguration

class ToolbarConfigurationHandler() {
    fun applyConfiguration(mainActivity: MainActivity, configuration: FragmentConfiguration) {
        configuration.toolbar.titleResourceId?.let {
            mainActivity.actionBarTool.setTitle(mainActivity.getResourceText(it))
        }
        configuration.toolbar.showBackArrow?.let { showBackArrow ->
            if (showBackArrow) {
                mainActivity.actionBarTool.showBackArrow()
            } else {
                mainActivity.actionBarTool.hideBackArrow()
            }
        }
    }
}