package guideme.volunteers.ui.fragments.volunteer.details.presenters

import guideme.volunteers.domain.Human
import guideme.volunteers.domain.Person
import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.datasource.UserDataSource
import guideme.volunteers.helpers.dataservices.datasource.VolunteerDataSource
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.helpers.dataservices.errors.ErrorType
import guideme.volunteers.ui.activities.main.MainActivity
import guideme.volunteers.ui.fragments.base.Presenter
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class PersonPresenter(var human: Human? = null,
                      var userDataSource: UserDataSource? = null,
                      var volunteerDataSource: VolunteerDataSource? = null,
                      var mainActivity: MainActivity? = null) : Presenter {
    override fun onResume() {

    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    override fun onCreate() {
    }

    fun updatePerson(newPerson: Person) {
        val humanVal = human
        if (humanVal == null) {
            mainActivity?.showError(ErrorMessage(ErrorType.ILLEGAL_STATE_EXCEPTION))
            return
        }
        when (humanVal) {
            is User -> {
                handleUpdate(userDataSource?.update(User(person = newPerson)))
            }
            is Volunteer -> {
                handleUpdate(volunteerDataSource?.update(Volunteer(person = newPerson)))
            }
        }
    }

    private fun <T : Human> handleUpdate(observable: Observable<T>?) {
        observable?.subscribeBy(
                onNext = { human = it },
                onComplete = { mainActivity?.goBack() },
                onError = {
                    //TODO show error on main activity
                })
    }
}