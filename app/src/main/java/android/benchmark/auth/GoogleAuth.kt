package android.benchmark.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Observable

interface GoogleAuth {
    fun init(fragmentActivity: FragmentActivity)
    fun signIn(fragmentActivity: FragmentActivity) : Observable<SignInAuthResult>
    fun onActivityResult(requestCode: Int, data : Intent)
    fun isSignedIn(): Boolean
}