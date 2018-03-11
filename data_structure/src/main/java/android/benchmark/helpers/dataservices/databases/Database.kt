package android.benchmark.helpers.dataservices.databases

import android.benchmark.auth.SignInAuthResult
import android.benchmark.domain.Person
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
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