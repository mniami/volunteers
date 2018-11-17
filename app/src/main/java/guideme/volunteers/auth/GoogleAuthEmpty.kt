package guideme.volunteers.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import io.reactivex.Single

class GoogleAuthEmpty : GoogleAuth {
    override fun signOut(fragmentActivity: FragmentActivity): Single<Boolean> = Single.just(false)

    override var authResult: SignInAuthResult
        get() = SignInAuthResult(false, AuthUser.createEmpty())
        set(_) {}

    override fun signIn(fragmentActivity: FragmentActivity): Single<SignInAuthResult> = Single.just(SignInAuthResult.createEmpty())

    override fun isSignedIn(): Boolean = true

    override fun onActivityResult(requestCode: Int, data: Intent) {
    }

    override fun init(fragmentActivity: FragmentActivity) {
    }
}