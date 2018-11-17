package guideme.volunteers.databases.actions

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import guideme.volunteers.domain.Volunteer
import io.reactivex.Single
import io.reactivex.SingleEmitter

class AddVolunteer(private val volunteer: Volunteer) {
    fun execute(database: FirebaseDatabase): Single<Volunteer> {
        return Single.create<Volunteer> {
            addVolunteer(database, it)
        }
    }

    private fun addVolunteer(database: FirebaseDatabase, emitter: SingleEmitter<Volunteer>) {
        var id: String? = volunteer.id
        if (volunteer.id.isEmpty()) {
            id = database.reference.child(DatabaseTables.VOLUNTEERS).push().key
        }

        if (id == null) {
            throw IllegalStateException()
        }
        val copiedVolunteer = volunteer.cloneWithId(id)
        val user = FirebaseAuth.getInstance().currentUser
        val userid = user!!.uid
        database.reference.child(userid).child(DatabaseTables.VOLUNTEERS).child(id).setValue(copiedVolunteer)
                .addOnFailureListener {
                    emitter.onError(Error(it.localizedMessage))
                }
                .addOnCompleteListener {
                    emitter.onSuccess(volunteer)
                }
    }
}