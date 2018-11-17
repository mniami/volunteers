package guideme.volunteers.databases.actions

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.errors.ElementNotFoundException
import io.reactivex.Single
import io.reactivex.SingleEmitter

class DeleteVolunteer(private val volunteer: Volunteer) {
    fun execute(database: FirebaseDatabase): Single<Volunteer> {
        return Single.create<Volunteer> {
            deleteVolunteer(database, it)
        }
    }

    private fun deleteVolunteer(database: FirebaseDatabase, emitter: SingleEmitter<Volunteer>) {
        if (volunteer.id.isEmpty()) {
            emitter.onError(ElementNotFoundException(volunteer, "Volunteer without id cannot be removed"))
            return
        }
        val user = FirebaseAuth.getInstance().currentUser
        val userid = user!!.uid
        database.reference.child(userid).child(DatabaseTables.VOLUNTEERS)
                .child(volunteer.id)
                .removeValue()
                .addOnFailureListener {
                    emitter.onError(Error(it.localizedMessage))
                }
                .addOnCompleteListener {
                    emitter.onSuccess(volunteer)
                }
    }
}