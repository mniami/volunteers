package android.benchmark.ui.fragments.settings

import android.benchmark.R
import android.benchmark.ui.activities.main.IMainActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragment : Fragment() {
    val mainActivity by lazy { activity as IMainActivity }
    val presenter = AuthenticationPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.authentication_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()

        sign_up_layout.visibility = if (presenter.needsToSignUp()) VISIBLE else GONE
        sign_in_layout.visibility = if (presenter.needsToSignIn()) VISIBLE else GONE
        sign_out_layout.visibility = if (presenter.signedIn()) VISIBLE else GONE
    }
}