package android.benchmark.ui.activities.main

import android.benchmark.auth.GoogleAuth
import android.support.v4.app.FragmentActivity
import io.reactivex.rxkotlin.subscribeBy


internal class MainPresenter(val mainView: MainView, val googleAuth: GoogleAuth, val fragmentActivity: FragmentActivity) :
        IMainPresenter {
    override fun onAuthenticationClick() = mainView.openAuthentication()

    override fun onSettingsClick() = mainView.openSettings()

    override fun onCreate() {
        mainView.showVolunteerList()
        authenticate()
    }

    private fun authenticate() {
        googleAuth.signIn(fragmentActivity).subscribeBy(
            onNext = {
                mainView.updateUserStatus(it)
            }
        )
    }
}

