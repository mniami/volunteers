package android.benchmark.helpers.databases.actions

import android.benchmark.domain.User
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.ObservableEmitter

class GetUserAction(private val database : FirebaseDatabase) {
    private val TAG = "GetUserAction"

    fun getUserAsync(name: String, emitter: ObservableEmitter<User>) {
        val ref = database.reference.child("users").child(name)
        val eventListener = object : ValueEventListener {
            override fun onDataChange(var1: DataSnapshot) {
                val user = var1.getValue(User::class.java)
                if (user != null) {
                    user.key = var1.key
                    Log.d(TAG, "person found '${user.name}'")
                    emitter.onNext(user)
                } else {
                    Log.d(TAG, "person not found")
                }
                ref.removeEventListener(this)
                emitter.onComplete()
            }

            override fun onCancelled(var1: DatabaseError) {
                ref.removeEventListener(this)
                emitter.onComplete()
            }
        }
        ref.addValueEventListener(eventListener)
    }
}