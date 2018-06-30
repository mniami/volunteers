package android.benchmark.ui.fragments.volunteer.details.presenters

import android.benchmark.domain.Human
import android.benchmark.domain.Person
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.datasource.UserDataSource
import android.benchmark.helpers.dataservices.datasource.VolunteerDataSource
import android.benchmark.helpers.dataservices.errors.ErrorMessage
import android.benchmark.helpers.dataservices.errors.ErrorType
import android.benchmark.ui.activities.main.MainActivity
import android.benchmark.ui.fragments.base.Presenter
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