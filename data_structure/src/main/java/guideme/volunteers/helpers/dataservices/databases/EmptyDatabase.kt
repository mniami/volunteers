package guideme.volunteers.helpers.dataservices.databases

import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.errors.UserNotSignedInException
import io.reactivex.Observable
import io.reactivex.Single

class EmptyDatabase : Database {
    override fun deleteVolunteer(volunteer: Volunteer): Single<Volunteer> {
        return Single.just(volunteer)
    }

    override fun updateVolunteer(volunteer: Volunteer): Single<Volunteer> {
        return Single.just(volunteer)
    }

    override fun setUser(user: User): Single<User> {
        return Single.just(user)
    }

    override fun getVolunteers(): Observable<Volunteer> {
        return Observable.create { emitter ->
            emitter.onComplete()
        }
    }

    override fun getCurrentUser(): Single<User> {
        return Single.error(UserNotSignedInException())
    }

    override fun addListener(databaseListener: IDatabaseListener) {
    }

    override fun removeListener(databaseListener: IDatabaseListener) {
    }

    override fun getUser(name: String): Observable<User> {
        return Observable.create { emitter ->
            emitter.onComplete()
        }
    }

    override fun init() {
    }

    override fun signOut() {
    }
}