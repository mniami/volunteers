package guideme.volunteers.ui.activities.main

import android.support.v4.app.FragmentActivity
import guideme.volunteers.auth.GoogleAuth
import guideme.volunteers.helpers.dataservices.databases.Database
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.helpers.dataservices.errors.ErrorType
import io.reactivex.rxkotlin.subscribeBy

internal class MainPresenter(
        val mainView: MainView,
        val googleAuth: GoogleAuth,
        val database: Database,
        val fragmentActivity: FragmentActivity) :
        IMainPresenter {
    override fun onAuthenticationClick() = mainView.openAuthentication()

    override fun onSettingsClick() = mainView.openSettings()

    override fun onCreate() {
        database.init()
    }

    override fun onStart() {
        if (!googleAuth.isSignedIn()) {
            googleAuth.signIn(fragmentActivity).subscribeBy(
                    onSuccess = {
                        mainView.refreshMenu()
                        mainView.showVolunteerList()
                    },
                    onError = {
                        mainView.showError(ErrorMessage(ErrorType.AUTHENTICATION_FAILED, it.message))
                    })
        }
    }
}

