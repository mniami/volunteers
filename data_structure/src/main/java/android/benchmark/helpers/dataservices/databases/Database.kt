package android.benchmark.helpers.dataservices.databases

import android.benchmark.auth.SignInAuthResult
import android.benchmark.domain.User
import io.reactivex.Observable

interface Database {
    fun getCurrentUserAsync() : Observable<User>
    fun addListener(databaseListener: IDatabaseListener)
    fun removeListener(databaseListener: IDatabaseListener)
    fun getUser(name: String): Observable<User>
    fun initAuth()
    fun signOut()
}