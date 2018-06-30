package guideme.volunteers.helpers.databases.actions

import guideme.volunteers.domain.User
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.ObservableEmitter

class GetUser(private val database : FirebaseDatabase) {
    private val TAG = "GetUserAction"

    fun getUserAsync(user: User, emitter: ObservableEmitter<User>) {
        val ref = database.reference.child(DatabaseTables.USERS).child(user.id)
        val eventListener = object : ValueEventListener {
            override fun onDataChange(var1: DataSnapshot) {
                val u = var1.getValue(User::class.java)
                if (u != null) {
                    Log.d(TAG, "person found '${u.person.name}'")
                    emitter.onNext(u)
                    emitter.onComplete()
                } else {
                    ref.removeEventListener(this)
                    AddUser(database).update(user, emitter)
                }
            }

            override fun onCancelled(var1: DatabaseError) {
                ref.removeEventListener(this)
                emitter.onComplete()
            }
        }
        ref.addValueEventListener(eventListener)
    }
}