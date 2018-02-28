package android.benchmark.ui.fragments.volunteer.details.presenters

import android.benchmark.domain.Person
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.datasource.UserDataSource
import android.benchmark.helpers.dataservices.datasource.VolunteerDataSource
import android.benchmark.ui.activities.main.MainActivity
import android.benchmark.ui.fragments.base.Presenter
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

class UserPresenter(var person: Person? = null,
                    var userDataSource: UserDataSource? = null,
                    var volunteerDataSource: VolunteerDataSource? = null,
                    var mainActivity: MainActivity? = null) : Presenter() {
    fun onSave() {
        val p = person
        when (p) {
            is User ->
                handleUpdate(userDataSource?.update(p))
            is Volunteer ->
                handleUpdate(volunteerDataSource?.update(p))
        }
    }

    private fun <T : Person> handleUpdate(observable : Observable<T>?){
        observable?.subscribeBy(
                onNext = { person = it },
                onComplete = { mainActivity?.goBack() },
                onError = {
                    //TODO show error on main activity
                })
    }
}