package android.benchmark.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import io.reactivex.Observable

interface GoogleAuth {
    var signInAuthResult : SignInAuthResult
    
    fun init(fragmentActivity: FragmentActivity)
    fun signIn(fragmentActivity: FragmentActivity) : Observable<SignInAuthResult>
    fun onActivityResult(requestCode: Int, data : Intent)
    fun isSignedIn(): Boolean
}