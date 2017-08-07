package android.benchmark.helpers.databases

import android.benchmark.domain.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable

class FirebaseDatabaseImpl : Database {
    private var databaseListener: IDatabaseListener? = null
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var snapshot: DataSnapshot? = null

    override fun getUser(name: String): Observable<User> {
        return Observable.create { emitter -> run {
                val ref = database.reference.child("users").child(name)
                val eventListener = object : ValueEventListener {
                    override fun onDataChange(var1: DataSnapshot) {
                        emitter.onNext(var1.getValue(User::class.java))
                        emitter.onComplete()
                        ref.removeEventListener(this)
                    }

                    override fun onCancelled(var1: DatabaseError) {
                        emitter.onError(var1.createReadException())
                        ref.removeEventListener(this)
                    }
                }
                emitter.setCancellable {
                    ref.removeEventListener(eventListener)
                }
                ref.addValueEventListener(eventListener)
            }
        }
    }

    override fun addListener(databaseListener: IDatabaseListener) {
        this.databaseListener = databaseListener
    }

    override fun removeListener(databaseListener: IDatabaseListener) {
        this.databaseListener = databaseListener
    }
}