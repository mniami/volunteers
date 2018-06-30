package android.benchmark.helpers.databases.actions

import android.benchmark.domain.Volunteer
import android.benchmark.helpers.databases.FirebaseDatabaseImpl
import android.benchmark.helpers.dataservices.databases.Database
import android.provider.ContactsContract

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