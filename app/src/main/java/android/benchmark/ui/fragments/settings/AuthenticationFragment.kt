package android.benchmark.ui.fragments.settings

import android.benchmark.ui.activities.main.IMainActivity
import android.support.v4.app.Fragment
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragment : Fragment() {
    val mainActivity by lazy { activity as IMainActivity }
    val presenter = AuthenticationPresenter()

    override fun onResume() {
        super.onResume()

        sign_up_layout.visibility = if (presenter.needsToSignUp()) VISIBLE else GONE
        sign_in_layout.visibility = if (presenter.needsToSignIn()) VISIBLE else GONE
        sign_out_layout.visibility = if (presenter.signedIn()) VISIBLE else GONE
    }
}