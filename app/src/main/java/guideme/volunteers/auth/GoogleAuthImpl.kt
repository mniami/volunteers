package guideme.volunteers.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import guideme.volunteers.R
import guideme.volunteers.log.createLog
import io.reactivex.Single
import io.reactivex.SingleEmitter
import java.util.*

class GoogleAuthImpl(val auth: guideme.volunteers.auth.Auth, override var authResult: SignInAuthResult) : GoogleAuth, GoogleApiClient.OnConnectionFailedListener {

    private val RC_SIGN_IN = 13234
    private val log = createLog(this)

    private var apiClient: GoogleApiClient? = null
    private var emitter: SingleEmitter<SignInAuthResult>? = null

    override fun init(fragmentActivity: FragmentActivity) {
        if (apiClient != null) {
            return
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestProfile()
                .requestIdToken(fragmentActivity.getString(R.string.default_web_client_id))
                .build()

        apiClient = GoogleApiClient.Builder(fragmentActivity)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun isSignedIn(): Boolean = authResult.success

    override fun signIn(fragmentActivity: FragmentActivity): Single<SignInAuthResult> {
        return Single.create { emitter ->
            if (authResult.success) {
                emitter.onSuccess(authResult)
            } else {
                this.emitter = emitter
                startActivity(fragmentActivity)
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        log.d { "Google auth connection failed" }

        emitter?.onError(Exception("Connection failed"))
    }

    override fun onActivityResult(requestCode: Int, data: Intent) {
        if (requestCode != RC_SIGN_IN) {
            return
        }

        val result = data.extras["extra_idp_response"]
        if (result !is IdpResponse) {
            emitter?.onError(IllegalStateException("No idp response found"))
            return
        }
        log.d { "Authentication data found" }
        if (result.idpToken == null) {
            log.d { "No idp token found in authentication response" }

            emitter?.onError(AuthException("Authentication failed"))
            return
        }

        auth.authUser = AuthUser("",
                "",
                result.email ?: "",
                result.idpSecret ?: "",
                result.idpToken ?: "")

        authResult = SignInAuthResult(true, auth.authUser)

        emitter?.onSuccess(authResult)
    }

    override fun signOut(fragmentActivity: FragmentActivity): Single<Boolean> {
        return Single.create { emitter ->
            AuthUI.getInstance().signOut(fragmentActivity).addOnCompleteListener {
                authResult = SignInAuthResult(false, AuthUser.createEmpty())
                emitter.onSuccess(true)
            }
        }
    }

    private fun startActivity(fragmentActivity: FragmentActivity) {
        fragmentActivity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Arrays.asList(AuthUI.IdpConfig.GoogleBuilder().build()))
                        .build(), RC_SIGN_IN)
    }
}
