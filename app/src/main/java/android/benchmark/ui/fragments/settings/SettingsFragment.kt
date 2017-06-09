package android.benchmark.ui.fragments.settings

import android.benchmark.R
import android.benchmark.domain.User
import android.benchmark.services.Services
import android.benchmark.services.dataservices.IDataService
import android.benchmark.ui.activities.main.IMainActivity
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.base.ToolbarConfiguration
import android.benchmark.ui.utils.AppVersionProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.TextView
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment : BaseFragment<SettingsPresenter>(), ISettingsFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SettingsPresenter(AppVersionProvider.fromActivity(activity), Services.dataService, mainActivity)
        configuration = FragmentConfiguration
                .withLayout(R.layout.settings_fragment)
                .title(R.string.action_settings)
                .showBackArrow()
                .create()
    }

    override fun setAppVersion(appVersion: String) {
        tvAppVersion.text = appVersion
    }

    override fun showUserData(user: User?) {
        user?.let {

        }
    }
}
