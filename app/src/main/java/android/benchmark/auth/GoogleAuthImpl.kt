package android.benchmark.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

class GoogleAuthImpl : GoogleAuth, GoogleApiClient.OnConnectionFailedListener {
    val TAG = "GoogleAuthImpl"
    val RC_SIGN_IN = 13234
    var apiClient: GoogleApiClient? = null

    override fun init(fragmentActivity: FragmentActivity) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        apiClient = GoogleApiClient.Builder(fragmentActivity)
                .enableAutoManage(fragmentActivity /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
    }

    override fun signIn(fragmentActivity: FragmentActivity) {
        apiClient?.let {
            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(it)
            signInIntent?.let {
                fragmentActivity.startActivityForResult(it, RC_SIGN_IN)
            }
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.w("MYAPP", "Google auth connection failed")
    }

    override fun onActivityResult(requestCode: Int, data: Intent) {
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result)
        }
    }

    private fun handleSignInResult(result: GoogleSignInResult) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess)

        if (result.isSuccess) {
            // Signed in successfully, show authenticated UI.
            val acct = result.signInAccount
            acct?.let {
                //todo notify about signing in
            }
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct!!.displayName))
            //updateUI(true)
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false)
        }
    }
}