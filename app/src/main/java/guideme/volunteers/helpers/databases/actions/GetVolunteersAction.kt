package guideme.volunteers.helpers.databases.actions

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.log.createLog
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import java.util.concurrent.TimeUnit

class GetVolunteersAction(private val database: FirebaseDatabase, private val timeout: Long) {
    private val log = createLog(this)

    fun getVolunteers(): Observable<Volunteer> {
        val obs = Observable.create<Volunteer> { emitter ->
            loadData(emitter)
        }
        return obs.timeout(timeout, TimeUnit.MILLISECONDS)
    }

    private fun loadData(emitter: ObservableEmitter<Volunteer>) {
        val ref = database.reference.child(DatabaseTables.VOLUNTEERS)
        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                reportLoadedData(emitter, dataSnapshot)
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

    private fun reportLoadedData(emitter: ObservableEmitter<Volunteer>, dataSnapshot: DataSnapshot) {
        log.d {
            "Volunteers count: ${dataSnapshot.children.count()}"
        }
        dataSnapshot.children
                .forEach {
                    val volunteer = it.getValue(Volunteer::class.java)
                    if (volunteer != null) {
                        emitter.onNext(volunteer)
                    }
                }
    }
}

