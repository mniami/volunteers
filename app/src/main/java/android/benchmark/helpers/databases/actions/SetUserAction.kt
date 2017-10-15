package android.benchmark.helpers.databases.actions

import android.benchmark.domain.User
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.ObservableEmitter

class SetUserAction(private val database: FirebaseDatabase) {
    private val TAG = "SetUserAction"

    fun setUserAsync(user: User, emitter: ObservableEmitter<User>) {
        val key = database.reference.child("users").push().key
        database.reference.child(key).setValue(user).addOnCompleteListener {
            emitter.onComplete()
        }
    }
}