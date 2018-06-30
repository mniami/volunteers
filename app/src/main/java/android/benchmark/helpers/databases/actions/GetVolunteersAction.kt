package android.benchmark.helpers.databases.actions

import android.benchmark.domain.Volunteer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class GetVolunteersAction(private val database: FirebaseDatabase, private val timeout: Long)  {
    fun getVolunteers(): Observable<Volunteer> {
        val obs = Observable.create<Volunteer> { emitter ->
            val ref = database.reference.child(DatabaseTables.VOLUNTEERS)
            val eventListener = object : ValueEventListener {
                override fun onDataChange(var1: DataSnapshot) {
                    var1.children
                            .forEach {
                                val volunteer = it.getValue(Volunteer::class.java)
                                if (volunteer != null) {
                                    emitter.onNext(volunteer)
                                }
                            }
                    ref.removeEventListener(this)
                    emitter.onComplete()
                }

                override fun onCancelled(var1: DatabaseError) {
                    ref.removeEventListener(this)
                    emitter.onComplete()
                }
            }
            ref.addListenerForSingleValueEvent(eventListener)
            ref.addValueEventListener(eventListener)
        }
        return obs.timeout(timeout, TimeUnit.MILLISECONDS)
    }
}

