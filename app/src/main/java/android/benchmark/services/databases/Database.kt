package android.benchmark.services.databases

import android.benchmark.domain.User
import io.reactivex.Observable

interface Database {
    fun addListener(databaseListener: IDatabaseListener)
    fun removeListener(databaseListener: IDatabaseListener)
    fun getUser(name: String): Observable<User>
}