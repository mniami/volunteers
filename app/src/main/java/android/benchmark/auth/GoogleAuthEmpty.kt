package android.benchmark.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity

class GoogleAuthEmpty : GoogleAuth {
    override fun onActivityResult(requestCode: Int, data : Intent) {
    }

    override fun signIn(fragmentActivity: FragmentActivity) {

    }

    override fun init(fragmentActivity: FragmentActivity) {
    }
}