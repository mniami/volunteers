package android.benchmark.ui.fragments.settings

import android.benchmark.auth.GoogleAuth
import android.benchmark.auth.SignInAuthResult
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.ui.fragments.base.Presenter
import android.support.v4.app.FragmentActivity
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.Observable

class AuthenticationPresenter(
        val authenticationFragment: AuthenticationFragment,
        val googleAuth: GoogleAuth,
        val database: Database) :
        Presenter() {

}