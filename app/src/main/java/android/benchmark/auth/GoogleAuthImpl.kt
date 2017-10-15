package android.benchmark.auth

import android.androidkotlinbenchmark.R
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.*

class GoogleAuthImpl(val auth: android.benchmark.auth.Auth, override var signInAuthResult: SignInAuthResult) : GoogleAuth, GoogleApiClient.OnConnectionFailedListener {
    private class SingingProcess(val observableEmitter: ObservableEmitter<SignInAuthResult>)

    private val TAG = "GoogleAuthImpl"
    private val RC_SIGN_IN = 13234

    private var apiClient: GoogleApiClient? = null
    private var singingProcess: SingingProcess? = null
    private var googleSignInAccount: GoogleSignInAccount? = null
    private var authenticationObservable : Observable<SignInAuthResult>? = null

    override fun init(fragmentActivity: FragmentActivity) {
        if (apiClient == null) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestIdToken(fragmentActivity.getString(R.string.default_web_client_id))
                    .build()

            apiClient = GoogleApiClient.Builder(fragmentActivity)
                    .enableAutoManage(fragmentActivity, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .build()
        }
    }

    override fun isSignedIn(): Boolean {
        return signInAuthResult.success
    }

    override fun signIn(fragmentActivity: FragmentActivity): Observable<SignInAuthResult> {
        var authObs = authenticationObservable
        if (authObs == null){
            authObs = Observable.create { emitter ->
                val singInResult = signInAuthResult

                if (singInResult.success) {
                    emitter.onNext(singInResult)
                    emitter.onComplete()
                    authenticationObservable = null
                } else {
                    startSignInProcess(fragmentActivity, emitter)
                }
            }
            authenticationObservable = authObs
            return authObs
        }
        return authObs
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.w("MYAPP", "Google auth connection failed")

        singingProcess?.observableEmitter?.let {
            it.onError(Exception("Connection failed"))
        }
    }

    override fun onActivityResult(requestCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result == null){
                singingProcess?.observableEmitter?.onComplete()
                return
            }
            Log.d(TAG, "handleSignInResult:" + result.isSuccess)

            if (result.isSuccess && result.signInAccount != null) {
                googleSignInAccount = result.signInAccount
            }

            singingProcess?.observableEmitter?.let {
                val lDisplayName = result.signInAccount?.displayName ?: ""
                val email = result.signInAccount?.email ?: ""
                val photoUrl = result.signInAccount?.photoUrl.toString()
                val id = result.signInAccount?.id ?: ""
                val idToken = result.signInAccount?.idToken ?: ""

                auth.authUser = AuthUser(lDisplayName, photoUrl, email, id, idToken)

                val lSingInResult = SignInAuthResult(result.isSuccess, auth.authUser)

                signInAuthResult = lSingInResult
                singingProcess = null

                it.onNext(lSingInResult)
                it.onComplete()

                authenticationObservable = null
            }
        }
    }

    private fun startSignInProcess(fragmentActivity: FragmentActivity, emitter: ObservableEmitter<SignInAuthResult>) {
        singingProcess = SingingProcess(emitter)

        apiClient?.let {
            fragmentActivity.startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                            AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()))
                            .build(), RC_SIGN_IN)
        }
    }
}