package guideme.volunteers.ui.activities.main

import guideme.volunteers.auth.GoogleAuth
import guideme.volunteers.domain.Person
import guideme.volunteers.domain.Privilege
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.domain.VolunteerType
import guideme.volunteers.helpers.Container
import guideme.volunteers.helpers.databases.actions.AddVolunteer
import guideme.volunteers.helpers.dataservices.databases.Database
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.helpers.dataservices.errors.ErrorType
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
                        shortDescription = "There is nth to say"))).execute(Container.database,
                onFailure = {},
                onComplete = { onComplete() })
    }
}

