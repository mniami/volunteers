package guideme.volunteers.databases.actions

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import guideme.volunteers.domain.User
import guideme.volunteers.helpers.dataservices.errors.ActionCanceledException
import guideme.volunteers.log.createLog
import io.reactivex.Single
import io.reactivex.SingleEmitter

class GetUser(private val database: FirebaseDatabase) {
    private val log = createLog(this)

    fun execute(user: User) : Single<User> {
        return Single.create { emitter ->
            loadUser(user, emitter)
        }
    }

    private fun loadUser(user: User, emitter: SingleEmitter<User>) {
        val ref = database.reference.child(DatabaseTables.USERS).child(user.id)
        val eventListener = object : ValueEventListener {
            override fun onDataChange(var1: DataSnapshot) {
                val u = var1.getValue(User::class.java)
                if (u != null) {
                    log.d { "User found '${u.person.name}'" }
                    emitter.onSuccess(u)
                } else {
                    log.d { "User not found" }
                    ref.removeEventListener(this)
                    AddUser(database).update(user, emitter)
                }
            }

            override fun onCancelled(var1: DatabaseError) {
                log.d { "Loading user canceled" }
                ref.removeEventListener(this)
                emitter.onError(ActionCanceledException("Loading user canceled"))
            }
        }
        ref.addValueEventListener(eventListener)
    }
}