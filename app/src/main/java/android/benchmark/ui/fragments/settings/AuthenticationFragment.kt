package android.benchmark.ui.fragments.settings

import android.benchmark.R
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.ToolbarConfiguration
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragment : BaseFragment<AuthenticationPresenter>(
        R.layout.authentication_fragment,
        ToolbarConfiguration(R.string.authentication, true),
        AuthenticationPresenter()) {

    override fun onResume() {
        super.onResume()

        sign_up_layout.visibility = if (presenter.needsToSignUp()) VISIBLE else GONE
        sign_in_layout.visibility = if (presenter.needsToSignIn()) VISIBLE else GONE
        sign_out_layout.visibility = if (presenter.signedIn()) VISIBLE else GONE
    }
}