package guideme.volunteers.ui.activities.main

import guideme.volunteers.auth.SignInAuthResult

interface MainView : MainActivity {
    fun updateUserStatus(signInResult: SignInAuthResult)
    fun refreshMenu()
}