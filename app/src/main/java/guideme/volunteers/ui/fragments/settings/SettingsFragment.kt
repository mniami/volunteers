package guideme.volunteers.ui.fragments.settings

import guideme.volunteers.R
import guideme.volunteers.helpers.Container
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import android.content.Context
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : BaseFragment<SettingsPresenter>(), ISettingsFragment {
    init {
        presenter = SettingsPresenter(
                Container.appVersionProvider,
                mainActivity,
                Container.dataSourceContainer)
        configuration = FragmentConfiguration
                .withLayout(R.layout.settings_fragment)
                .title(R.string.action_settings)
                .showBackArrow()
                .create()
    }
}
