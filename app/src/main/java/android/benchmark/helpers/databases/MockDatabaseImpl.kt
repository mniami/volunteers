package android.benchmark.helpers.databases

import android.benchmark.domain.Action
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.Services
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.databases.IDatabaseListener
import android.benchmark.helpers.dataservices.datasource.MockUserDataSource
import android.benchmark.helpers.dataservices.datasource.MockVolunteersDataSource
import io.reactivex.Observable

/**
 * Created by DASZ on 10.02.2018.
 */
class MockDatabaseImpl : Database {
    override fun updateVolunteer(volunteer: Volunteer): Observable<Volunteer> {
        return Observable.create {
            Thread.sleep(200)
            volunteer.activity.actions.add(Action("Updated data"))
            it.onNext(volunteer)
            it.onComplete()
        }
    }

    override fun getCurrentUserAsync(): Observable<User> = MockUserDataSource().data.observable

    override fun addListener(databaseListener: IDatabaseListener) {
    }

    override fun removeListener(databaseListener: IDatabaseListener) {
    }

    override fun getUser(name: String): Observable<User> = MockUserDataSource().data.observable

    override fun getVolunteers(): Observable<Volunteer> = MockVolunteersDataSource().data.observable

    override fun init() {
    }

    override fun signOut() {
    }

    override fun setUser(user: User): Observable<User> = MockUserDataSource().data.observable
}