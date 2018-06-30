package guideme.volunteers.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import io.reactivex.Observable
import io.reactivex.Single

class GoogleAuthEmpty : GoogleAuth {
    override var authResult: SignInAuthResult
        get() = SignInAuthResult(false, AuthUser.createEmpty())
        set(value) {}

    override fun signIn(fragmentActivity: FragmentActivity): Single<SignInAuthResult> {
        return Single.just(SignInAuthResult.createEmpty())
    }

    override fun isSignedIn(): Boolean {
        return true
    }

    override fun onActivityResult(requestCode: Int, data: Intent) {
    }

    override fun init(fragmentActivity: FragmentActivity) {
    }
}