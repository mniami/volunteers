package android.benchmark.ui.fragments.settings

import android.benchmark.R
import android.benchmark.domain.User
import android.benchmark.services.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.utils.AppVersionProvider
import android.content.Context
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : BaseFragment<SettingsPresenter>(), ISettingsFragment {

    init {
        configuration = FragmentConfiguration
                .withLayout(R.layout.settings_fragment)
                .title(R.string.action_settings)
                .showBackArrow()
                .create()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = SettingsPresenter(AppVersionProvider.fromActivity(activity), Services.dataService, mainActivity)
    }

    override fun setAppVersion(appVersion: String) {
        tvAppVersion.text = appVersion
    }

    override fun showUserData(user: User?) {
        user?.let {

        }
    }
}
