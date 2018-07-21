package guideme.volunteers.helpers.dataservices.databases

import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import io.reactivex.Observable
import io.reactivex.Single

interface Database {
    fun getCurrentUser(): Single<User>
    fun addListener(databaseListener: IDatabaseListener)
    fun removeListener(databaseListener: IDatabaseListener)
    fun getVolunteers(): Observable<Volunteer>
    fun init()
    fun signOut()
    fun setUser(user: User): Single<User>
    fun updateVolunteer(volunteer: Volunteer): Single<Volunteer>
    fun deleteVolunteer(volunteer: Volunteer): Single<Volunteer>
}