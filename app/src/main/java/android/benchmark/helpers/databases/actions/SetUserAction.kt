package android.benchmark.helpers.databases.actions

import android.benchmark.domain.User
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.ObservableEmitter

class SetUserAction(private val database: FirebaseDatabase) {
    fun setUserAsync(updatedUser: User, emitter: ObservableEmitter<User>) {
        var user = updatedUser
        if (user.id.isEmpty()) {
            val key = database.reference.child("users").push().key
            user = user.copy(key, user.volunteers, user.person)
        }
        database.reference.child(user.id).setValue(user).addOnCompleteListener {
            emitter.onComplete()
        }
    }
    fun updateUserAsync(user : User, emitter: ObservableEmitter<User>){
        database.reference.child(user.id).setValue(user).addOnCompleteListener {
            emitter.onComplete()
        }
    }
}