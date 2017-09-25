package android.benchmark.helpers.dataservices.databases

import android.benchmark.domain.User
import io.reactivex.Observable

class EmptyDatabase : Database {
    override fun signIn(): Observable<User> {
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

    override fun initAuth() {
    }

    override fun signOut() {
    }
}