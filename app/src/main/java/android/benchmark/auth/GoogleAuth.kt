package android.benchmark.auth

import android.content.Intent
import android.support.v4.app.FragmentActivity

interface GoogleAuth {
    fun init(fragmentActivity: FragmentActivity)
    fun signIn(fragmentActivity: FragmentActivity)
    fun onActivityResult(requestCode: Int, data : Intent)
}