package android.benchmark.ui.fragments.settings

import android.benchmark.R
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.benchmark.ui.fragments.base.ToolbarConfiguration
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragment : BaseFragment<AuthenticationPresenter>() {

    init {
        configuration = FragmentConfiguration.withLayout(R.layout.authentication_fragment)
                .title(R.string.authentication)
                .showBackArrow()
                .create()
        presenter = AuthenticationPresenter()
    }

    override fun onResume() {
        super.onResume()
        presenter?.let {
            sign_up_layout.visibility = if (it.needsToSignUp()) VISIBLE else GONE
            sign_in_layout.visibility = if (it.needsToSignIn()) VISIBLE else GONE
            sign_out_layout.visibility = if (it.signedIn()) VISIBLE else GONE
        }
    }
}