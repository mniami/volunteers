package guideme.volunteers.helpers.dataservices.databases

import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import io.reactivex.Observable

class EmptyDatabase : Database {
    override fun updateVolunteer(volunteer: Volunteer): Observable<Volunteer> {
        return Observable.create {
            it.onComplete()
        }
    }

    override fun setUser(user: User): Observable<User> {
        return Observable.create { emitter ->
            emitter.onComplete()
        }
    }

    override fun getVolunteers(): Observable<Volunteer> {
        return Observable.create { emitter ->
            emitter.onComplete()
        }
    }

    override fun getCurrentUserAsync(): Observable<User> {
        return Observable.create { emitter ->
            emitter.onComplete()
        }
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