package guideme.volunteers.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import io.reactivex.Single

interface GoogleAuth {
    var authResult: SignInAuthResult

    fun init(fragmentActivity: FragmentActivity)
    fun signIn(fragmentActivity: FragmentActivity): Single<SignInAuthResult>
    fun onActivityResult(requestCode: Int, data: Intent)
    fun isSignedIn(): Boolean
}