package android.benchmark.ui.fragments.settings

import android.benchmark.services.cache.LocalDataCache
import android.benchmark.services.dataservices.DataService
import android.benchmark.ui.fragments.base.Presenter
import com.facebook.login.LoginResult

class AuthenticationPresenter(
        val dataService: DataService,
        val authenticationFragment: AuthenticationFragment,
        val dataCache: LocalDataCache) :
        Presenter() {
    companion object {
        val NOT_SIGNED_UP = 1
        val NOT_SIGNED_IN = 2
        val SIGNED_IN = 3
        val UNKNOWN_STATE = 0
    }
    var state = UNKNOWN_STATE

    fun needsToSignUp() = state == NOT_SIGNED_UP

    fun needsToSignIn() = state == NOT_SIGNED_IN

    fun signedIn() = state == SIGNED_IN

    override fun onCreate() {
        super.onCreate()
        if (state == UNKNOWN_STATE) {

        }
    }

    fun onAuthenticationSuccess(loginResult: LoginResult) {

    }
}