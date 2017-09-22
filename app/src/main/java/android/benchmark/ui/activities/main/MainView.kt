package android.benchmark.ui.activities.main

import android.benchmark.auth.SignInAuthResult

interface MainView : MainActivity {
    fun updateUserStatus(signInResult: SignInAuthResult)
}