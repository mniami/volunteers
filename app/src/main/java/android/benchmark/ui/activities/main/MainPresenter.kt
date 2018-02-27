package android.benchmark.ui.activities.main

import android.benchmark.auth.GoogleAuth
import android.benchmark.domain.Person
import android.benchmark.domain.Privilege
import android.benchmark.domain.Volunteer
import android.benchmark.domain.VolunteerType
import android.benchmark.helpers.Services
import android.benchmark.helpers.databases.actions.AddVolunteer
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.errors.ErrorMessage
import android.benchmark.helpers.dataservices.errors.ErrorType
import android.support.v4.app.FragmentActivity
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
                    onComplete = {
                        addTestsVolunteers {
                            mainView.refreshMenu()
                            mainView.showVolunteerList()
                        }
                    },
                    onError = { ex ->
                        mainView.showError(ErrorMessage(ErrorType.NO_INTERNET_CONNECTION))
                    })
        }
    }

    private fun addTestsVolunteers(volunteerNumber: Int = 0, onComplete: () -> Unit) {
        if (volunteerNumber == 0) {
            onComplete()
        } else {
            addVolunteer { addTestsVolunteers(volunteerNumber + 1, onComplete) }
        }
    }

    private fun addVolunteer(onComplete: () -> Unit) {
        AddVolunteer(Volunteer(
                volunteerType = VolunteerType.Junior,
                person = Person(name = "Damian",
                        surname = "Szczepanski",
                        description = "The volunteer we need",
                        email = "d.szczepek@gmail.com",
                        privilege = Privilege.USER,
                        shortDescription = "There is nth to say"))).execute(Services.instance.database,
                onFailure = {},
                onComplete = { onComplete() })
    }
}

