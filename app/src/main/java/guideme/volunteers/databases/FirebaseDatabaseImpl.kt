package guideme.volunteers.databases

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import guideme.volunteers.databases.actions.*
import guideme.volunteers.domain.User
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.databases.Database
import guideme.volunteers.helpers.dataservices.databases.IDatabaseListener
import guideme.volunteers.helpers.dataservices.errors.DatabaseIllegalStateException
import guideme.volunteers.helpers.dataservices.errors.UserNotSignedInException
import io.reactivex.Observable
import io.reactivex.Single

class FirebaseDatabaseImpl(private val timeout: Long = 10000) : Database {

    private var databaseListener: IDatabaseListener? = null
    private val firebaseDb: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var firebaseAuth: FirebaseAuth? = null
    private val getVolunteersAction = GetVolunteers(firebaseDb, timeout)
    private val setUserAction = AddUser(firebaseDb)

    override fun init() {
        if (firebaseAuth == null) {
            firebaseAuth = FirebaseAuth.getInstance()
        }
    }

    override fun getCurrentUser(): Single<User> {
        val auth = firebaseAuth
        if (auth == null) {
            return Single.error(DatabaseIllegalStateException())
        }
        val user = auth.currentUser
        if (user != null && user.uid.isNotEmpty()) {
            return GetUser(firebaseDb).execute(User(id = user.uid))
        } else {
            return Single.error(UserNotSignedInException())
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

    override fun setUser(user: User): Single<User> {
        return Single.create { emitter ->
            setUserAction.update(user, emitter)
        }
    }

    override fun getVolunteers(): Observable<Volunteer> = getVolunteersAction.getVolunteers()
    override fun updateVolunteer(volunteer: Volunteer): Single<Volunteer> = AddVolunteer(volunteer).execute(firebaseDb)
    override fun deleteVolunteer(volunteer: Volunteer): Single<Volunteer> = DeleteVolunteer(volunteer).execute(firebaseDb)
}