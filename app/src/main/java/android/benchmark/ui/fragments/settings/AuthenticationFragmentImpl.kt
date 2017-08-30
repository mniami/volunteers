package android.benchmark.ui.fragments.settings

import android.androidkotlinbenchmark.R
import android.benchmark.auth.SignInAuthResult
import android.benchmark.helpers.Services
import android.benchmark.ui.fragments.base.BaseFragment
import android.benchmark.ui.fragments.base.FragmentConfiguration
import android.view.View
import com.facebook.CallbackManager
import com.squareup.picasso.Picasso
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.authentication_fragment.*

class AuthenticationFragmentImpl : BaseFragment<AuthenticationPresenter>(), AuthenticationFragment {
    init {
        configuration = FragmentConfiguration.withLayout(R.layout.authentication_fragment)
                .title(R.string.authentication)
                .showBackArrow()
                .create()
        presenter = AuthenticationPresenter(this, Services.instance.googleAuth)
    }

    override fun onResume() {
        super.onResume()

        signIn()

        presenter?.let {
            googleLoginButton?.setOnClickListener {
                signIn()
            }
            facebookLoginButton?.setReadPermissions("email", "public_profile")
            facebookLoginButton?.registerCallback(CallbackManager.Factory.create(), it.createFacebookCallback())
        }
    }

    private fun signIn(){
        presenter?.singIn(activity)?.subscribeBy(
                onNext = {
                    updateUi(it)
                }
        )
    }

    private fun updateUi(result: SignInAuthResult) {
        signedLayout.visibility = if (result.success) View.VISIBLE else View.GONE
        signInLayout.visibility = if (result.success) View.GONE else View.VISIBLE

        tvHeader.text = if (result.success) result.authUser.name else ""
        tvShortDescription.text = if (result.success) result.authUser.email else ""

        if (result.success) {
            Picasso.with(context).load(result.authUser.photoUrl).into(ivImage)
        }
    }
}