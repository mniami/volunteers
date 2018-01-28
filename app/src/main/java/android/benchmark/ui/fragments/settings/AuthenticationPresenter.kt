package android.benchmark.ui.fragments.settings

import android.benchmark.auth.GoogleAuth
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.ui.fragments.base.BasicPresenter

class AuthenticationPresenter(
        val authenticationFragment: AuthenticationFragment,
        val googleAuth: GoogleAuth,
        val database: Database) :
        BasicPresenter() {

}