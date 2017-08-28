package android.benchmark.ui.fragments.settings

import android.benchmark.auth.GoogleAuth
import android.benchmark.auth.SignInAuthResult
import android.benchmark.ui.fragments.base.Presenter
import android.support.v4.app.FragmentActivity
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import io.reactivex.Observable

class AuthenticationPresenter(
        val authenticationFragment: AuthenticationFragment,
        val googleAuth: GoogleAuth) :
        Presenter() {

    fun isSignedIn(): Boolean {
        return googleAuth.isSignedIn()
    }

    fun singIn(fragmentActivity: FragmentActivity): Observable<SignInAuthResult> {
        return googleAuth.signIn(fragmentActivity)
    }

    fun createFacebookCallback(): FacebookCallback<LoginResult> {
        return object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {

            }

            override fun onCancel() {

            }

            override fun onError(var1: FacebookException) {

            }
        }
    }
}