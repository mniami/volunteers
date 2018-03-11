package android.benchmark.helpers.databases

import android.benchmark.auth.Auth
import android.benchmark.domain.Person
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.databases.actions.GetUserAction
import android.benchmark.helpers.databases.actions.GetVolunteersActionImpl
import android.benchmark.helpers.databases.actions.SetUserAction
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.databases.IDatabaseListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class FirebaseDatabaseImpl(val authentication: Auth, val timeout: Long = 10000) : Database {
    private val TAG = "FirebaseDatabaseImpl"
    private var databaseListener: IDatabaseListener? = null
    val firebaseDb: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var firebaseAuth: FirebaseAuth? = null
    private val getUserAction = GetUserAction(firebaseDb)
    private val getVolunteersAction = GetVolunteersActionImpl(firebaseDb, timeout)
    private val setUserAction = SetUserAction(firebaseDb)

    override fun getCurrentUserAsync(): Observable<User> {
        return Observable.create<User>({ emitter ->
            val firebaseAuthInstance = firebaseAuth
            if (firebaseAuthInstance == null) {
                emitter.onComplete()
                return@create
            }
            val user = firebaseAuthInstance.currentUser
            if (user != null && user.uid.isNotBlank()) {
                getUserAction.getUserAsync(User(id = user.uid, person = Person(name = user.displayName ?: "", email = user.email ?: "")), emitter)
            } else {
                emitter.onComplete()
            }
        }).timeout(timeout, TimeUnit.MILLISECONDS)
    }

    override fun init() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }

    override fun signOut() {
        firebaseAuth?.signOut()
    }

    override fun addListener(databaseListener: IDatabaseListener) {
        this.databaseListener = databaseListener
    }

    override fun removeListener(databaseListener: IDatabaseListener) {
        this.databaseListener = databaseListener
    }

    override fun getUser(name: String): Observable<User> {
        return Observable.error { NotImplementedError() }
    }

    override fun setUser(user: User): Observable<User> {
        return Observable.create { emitter ->
            setUserAction.setUserAsync(user, emitter)
        }
    }

    override fun getVolunteers(): Observable<Volunteer> {
        return getVolunteersAction.getVolunteers()
    }

    override fun updateVolunteer(volunteer: Volunteer): Observable<Volunteer> {
        throw NotImplementedError()
    }
}