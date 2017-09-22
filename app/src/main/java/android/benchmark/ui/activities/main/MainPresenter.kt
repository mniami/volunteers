package android.benchmark.ui.activities.main

import android.benchmark.auth.GoogleAuth
import android.benchmark.helpers.dataservices.databases.Database
import android.support.v4.app.FragmentActivity

internal class MainPresenter(
        val mainView: MainView,
        val googleAuth: GoogleAuth,
        val database: Database,
        val fragmentActivity: FragmentActivity) :
        IMainPresenter {
    override fun onAuthenticationClick() = mainView.openAuthentication()

    override fun onSettingsClick() = mainView.openSettings()

    override fun onCreate() {
        mainView.showVolunteerList()
        database.initAuth()
    }

    override fun onStart(){
        authenticate()
    }

    private fun authenticate() {
        googleAuth.signIn(fragmentActivity).run{}
    }
}

