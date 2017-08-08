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
        presenter = AuthenticationPresenter(Services.instance.dataService, this, Services.instance.dataCache)
    }

    override fun onResume() {
        super.onResume()

        val mCallbackManager = CallbackManager.Factory.create()
        googleLoginButton?.setOnClickListener {
            Services.instance.googleAuth.signIn(activity)
        }
        facebookLoginButton?.setReadPermissions("email", "public_profile")
        facebookLoginButton?.registerCallback(mCallbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                presenter?.onAuthenticationSuccess(loginResult)
            }

            override fun onCancel() {}
            override fun onError(var1: FacebookException) {}
        })
    }
}

