package android.benchmark.helpers.databases.actions

import android.benchmark.domain.User
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.ObservableEmitter

class AddUser(private val database: FirebaseDatabase) {
    fun update(updatedUser: User, emitter: ObservableEmitter<User>) {
        var user = updatedUser
        var id = updatedUser.id

        if (user.id.isEmpty()) {
            val key = database.reference.child(DatabaseTables.USERS).push().key
            if (key == null){
                throw IllegalStateException()
            }
            id = key
        }
        database.reference.child(DatabaseTables.USERS).child(id).setValue(user).addOnCompleteListener {
            emitter.onNext(user)
            emitter.onComplete()
        }
    }
}