package guideme.volunteers.ui.activities.main

import android.support.v4.app.FragmentActivity
import guideme.volunteers.auth.GoogleAuth
import guideme.volunteers.helpers.dataservices.databases.Database
import io.reactivex.rxkotlin.subscribeBy

internal class MainPresenter(
        private val mainView: MainView,
        private val googleAuth: GoogleAuth,
        val database: Database,
        private val fragmentActivity: FragmentActivity) : IMainPresenter {
    override fun onAuthenticationClick() {
        signIn()
    }

    override fun onSettingsClick() = mainView.openSettings()

    override fun onCreate() {
        database.init()
        signIn()
    }

    private fun signIn() {
        if (!googleAuth.isSignedIn()) {
            googleAuth.signIn(fragmentActivity).subscribeBy(
                    onSuccess = {
                        mainView.refreshMenu()
                        mainView.showVolunteerList()
                    },
                    onError = {
                        mainView.openAuthentication()
                    })
        }
    }

    override fun onStart() {
    }
}

