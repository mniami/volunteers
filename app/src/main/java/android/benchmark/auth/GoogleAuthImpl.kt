package android.benchmark.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class GoogleAuthImpl(val auth: android.benchmark.auth.Auth, override var signInAuthResult: SignInAuthResult) : GoogleAuth, GoogleApiClient.OnConnectionFailedListener {
    private class SingingProcess(val observableEmitter: ObservableEmitter<SignInAuthResult>)

    private val TAG = "GoogleAuthImpl"
    private val RC_SIGN_IN = 13234

    private var apiClient: GoogleApiClient? = null
    private var singingProcess: SingingProcess? = null
    private var googleSignInAccount: GoogleSignInAccount? = null

    override fun init(fragmentActivity: FragmentActivity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        apiClient = GoogleApiClient.Builder(fragmentActivity)
                .enableAutoManage(fragmentActivity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun isSignedIn(): Boolean {
        return signInAuthResult.success
    }

    override fun signIn(fragmentActivity: FragmentActivity): Observable<SignInAuthResult> {
        return Observable.create { emitter ->
            val singInResult = signInAuthResult

            if (singInResult.success) {
                emitter.onNext(singInResult)
                emitter.onComplete()
            } else {
                startSignInProcess(fragmentActivity, emitter)
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.w("MYAPP", "Google auth connection failed")

        singingProcess?.observableEmitter?.let {
            it.onError(Exception("Connection failed"))
        }
    }

    override fun onActivityResult(requestCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.d(TAG, "handleSignInResult:" + result.isSuccess)

            if (result.isSuccess && result.signInAccount != null) {
                googleSignInAccount = result.signInAccount
            }

            singingProcess?.observableEmitter?.let {
                val lDisplayName = result.signInAccount?.displayName ?: ""
                val email = result.signInAccount?.email ?: ""
                val photoUrl = result.signInAccount?.photoUrl.toString()
                val id = result.signInAccount?.id ?: ""
                val idToken = "AAAAur7r6CA:APA91bHH5N7GApStTXB5jcuYCMZZicyFMWtLNv9dKUi4DZ2fSKbvyUeX8Y5IdTJn-tqhohGq5rUZatS_TgVGJSm7D-NILI85IVmMYYA3in8iD4B8N7uGj_4mK3CuHJMT5lHYomaTNNTg"

                auth.authUser = AuthUser(lDisplayName, photoUrl, email, id, idToken)

                val lSingInResult = SignInAuthResult(result.isSuccess, auth.authUser)

                signInAuthResult = lSingInResult
                singingProcess = null

                it.onNext(lSingInResult)
                it.onComplete()
            }
        }
    }

    private fun startSignInProcess(fragmentActivity: FragmentActivity, emitter: ObservableEmitter<SignInAuthResult>) {
        singingProcess = SingingProcess(emitter)

        apiClient?.let {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(it)
            signInIntent?.let {
                fragmentActivity.startActivityForResult(it, RC_SIGN_IN)
            }
        }
    }
}