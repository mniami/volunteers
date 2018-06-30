package guideme.volunteers.helpers.dataservices.databases

import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import io.reactivex.Observable

interface Database {
    fun getCurrentUserAsync() : Observable<User>
    fun addListener(databaseListener: IDatabaseListener)
    fun removeListener(databaseListener: IDatabaseListener)
    fun getUser(name: String): Observable<User>
    fun getVolunteers() : Observable<Volunteer>
    fun init()
    fun signOut()
    fun setUser(user: User) : Observable<User>
    fun updateVolunteer(volunteer: Volunteer): Observable<Volunteer>
}