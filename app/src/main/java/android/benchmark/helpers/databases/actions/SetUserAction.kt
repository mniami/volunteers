package android.benchmark.helpers.databases.actions

import android.benchmark.domain.User
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.ObservableEmitter

class SetUserAction(private val database: FirebaseDatabase) {
    fun setUserAsync(updatedUser: User, emitter: ObservableEmitter<User>) {
        var user = updatedUser
        var key = ""

        if (user.id.isEmpty()) {
            key = database.reference.child("users").push().key
        }
        database.reference.child("users").child(key).setValue(user).addOnCompleteListener {
            emitter.onNext(user)
            emitter.onComplete()
        }
    }

    fun updateUserAsync(user : User, emitter: ObservableEmitter<User>){
        database.reference.child("users").child(user.id).setValue(user).addOnCompleteListener {
            emitter.onNext(user)
            emitter.onComplete()
        }
    }
}