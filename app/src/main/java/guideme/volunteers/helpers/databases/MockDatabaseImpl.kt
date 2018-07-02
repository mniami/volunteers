package guideme.volunteers.helpers.databases

import guideme.volunteers.domain.Action
import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.databases.Database
import guideme.volunteers.helpers.dataservices.databases.IDatabaseListener
import guideme.volunteers.helpers.dataservices.datasource.MockUserDataSource
import guideme.volunteers.helpers.dataservices.datasource.MockVolunteersDataSource
import guideme.volunteers.log.createLog
import io.reactivex.Observable
import io.reactivex.Single

class MockDatabaseImpl : Database {
    private val log = createLog(this)

    override fun deleteVolunteer(volunteer: Volunteer): Single<Volunteer> {
        return Single.create {
            MockVolunteersDataSource.volunteers.remove(volunteer)
            it.onSuccess(volunteer)
        }
    }

    override fun updateVolunteer(volunteer: Volunteer): Single<Volunteer> {
        log.d { "update volunteer called ${volunteer.person.name}" }
        return Single.create {
            Thread.sleep(200)
            volunteer.person.activity.actions.add(Action("Updated data"))
            it.onSuccess(volunteer)
        }
    }

    override fun getCurrentUser(): Single<User> = MockUserDataSource().data.observable.firstOrError()

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

    override fun setUser(user: User): Single<User> {
        return MockUserDataSource().data.observable.firstOrError()
    }
}