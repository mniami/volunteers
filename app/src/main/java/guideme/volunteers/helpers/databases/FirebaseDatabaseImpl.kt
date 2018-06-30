package guideme.volunteers.helpers.databases

import guideme.volunteers.auth.Auth
import guideme.volunteers.domain.Person
import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.databases.actions.GetUser
import guideme.volunteers.helpers.databases.actions.GetVolunteersAction
import guideme.volunteers.helpers.databases.actions.AddUser
import guideme.volunteers.helpers.dataservices.databases.Database
import guideme.volunteers.helpers.dataservices.databases.IDatabaseListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class FirebaseDatabaseImpl(val authentication: Auth, val timeout: Long = 10000) : Database {
    private val TAG = "FirebaseDatabaseImpl"
    private var databaseListener: IDatabaseListener? = null
    val firebaseDb: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var firebaseAuth: FirebaseAuth? = null
    private val getUserAction = GetUser(firebaseDb)
    private val getVolunteersAction = GetVolunteersAction(firebaseDb, timeout)
    private val setUserAction = AddUser(firebaseDb)

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
            setUserAction.update(user, emitter)
        }
    }

    override fun getVolunteers(): Observable<Volunteer> {
        return getVolunteersAction.getVolunteers()
    }

    override fun updateVolunteer(volunteer: Volunteer): Observable<Volunteer> {
        throw NotImplementedError()
    }
}