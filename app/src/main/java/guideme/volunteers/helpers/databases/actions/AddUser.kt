package guideme.volunteers.helpers.databases.actions

import com.google.firebase.database.FirebaseDatabase
import guideme.volunteers.domain.User
import io.reactivex.SingleEmitter

class AddUser(private val database: FirebaseDatabase) {
    fun update(updatedUser: User, emitter: SingleEmitter<User>) {
        var user = updatedUser
        var id = updatedUser.id

        if (user.id.isEmpty()) {
            val key = database.reference.child(DatabaseTables.USERS).push().key
            if (key == null) {
                throw IllegalStateException()
            }
            id = key
        }
        database.reference.child(DatabaseTables.USERS).child(id).setValue(user).addOnCompleteListener {
            emitter.onSuccess(user)
        }
    }
}