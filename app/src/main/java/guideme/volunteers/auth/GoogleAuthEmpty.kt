package guideme.volunteers.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity
import io.reactivex.Observable

class GoogleAuthEmpty : GoogleAuth {
    override var signInAuthResult: SignInAuthResult
        get() = SignInAuthResult(false, AuthUser.createEmpty())
        set(value) {}

    override fun signIn(fragmentActivity: FragmentActivity): Observable<SignInAuthResult> {
        return Observable.create {
            it.onNext(SignInAuthResult.createEmpty())
            it.onComplete()
        }
    }

    override fun isSignedIn(): Boolean {
        return true
    }

    override fun onActivityResult(requestCode: Int, data : Intent) {
    }

    override fun init(fragmentActivity: FragmentActivity) {
    }
}