package android.benchmark.ui.fragments.settings

import android.benchmark.R
import android.benchmark.helpers.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.view.View.GONE
import android.view.View.VISIBLE
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragmentImpl : BaseFragment<AuthenticationPresenter>(), AuthenticationFragment {

    init {
        configuration = FragmentConfiguration.withLayout(R.layout.authentication_fragment)
                .title(R.string.authentication)
                .showBackArrow()
                .create()
        presenter = AuthenticationPresenter(this, Services.instance.dataCache)
    }

    override fun onResume() {
        super.onResume()

        val loginButton = activity.findViewById(R.id.facebook_login_button) as LoginButton
        val mCallbackManager = CallbackManager.Factory.create()

        loginButton.setReadPermissions("email", "public_profile")
        loginButton.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                presenter?.onAuthenticationSuccess(loginResult)
            }

            override fun onCancel() {}
            override fun onError(var1: FacebookException) {}
        })

        presenter?.let {
            sign_up_layout.visibility = if (it.needsToSignUp()) VISIBLE else GONE
            sign_in_layout.visibility = if (it.needsToSignIn()) VISIBLE else GONE
            sign_out_layout.visibility = if (it.signedIn()) VISIBLE else GONE
        }
    }
}

interface AuthenticationFragment