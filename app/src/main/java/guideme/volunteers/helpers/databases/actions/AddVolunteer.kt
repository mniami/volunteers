package guideme.volunteers.helpers.databases.actions

import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.databases.FirebaseDatabaseImpl
import guideme.volunteers.helpers.dataservices.databases.Database

class AddVolunteer(private val volunteer: Volunteer) {
    fun execute(database: Database, onFailure: (kotlin.Error) -> Unit, onComplete: (Volunteer) -> Unit) {
        if (database is FirebaseDatabaseImpl && volunteer.id.isEmpty()) {
            var id = database.firebaseDb.reference.child(DatabaseTables.VOLUNTEERS).push().key

            if (id == null){
                throw IllegalStateException()
            }

            database.firebaseDb.reference.child(DatabaseTables.VOLUNTEERS).child(id).setValue(volunteer)
                    .addOnFailureListener {
                        onFailure(Error(it.localizedMessage))
                    }
                    .addOnCompleteListener {
                        onComplete(volunteer)
                    }
        }
    }
}