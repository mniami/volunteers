package guideme.volunteers.databases.actions

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import guideme.volunteers.domain.User
import io.reactivex.SingleEmitter

class AddUser(private val database: FirebaseDatabase) {
    fun update(updatedUser: User, emitter: SingleEmitter<User>) {
        var id = updatedUser.id

        if (updatedUser.id.isEmpty()) {
            val key = database.reference.child(DatabaseTables.USERS).push().key
            if (key == null) {
                throw IllegalStateException()
            }
            id = key
        }
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val firebaseUserId = firebaseUser!!.uid
        database.reference.child(firebaseUserId).child(DatabaseTables.USERS).child(id).setValue(updatedUser).addOnCompleteListener {
            emitter.onSuccess(updatedUser)
        }
    }
}