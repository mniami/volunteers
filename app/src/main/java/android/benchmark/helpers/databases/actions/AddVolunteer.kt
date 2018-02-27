package android.benchmark.helpers.databases.actions

import android.benchmark.domain.Volunteer
import android.benchmark.helpers.databases.FirebaseDatabaseImpl
import android.benchmark.helpers.dataservices.databases.Database

class AddVolunteer(private val volunteer: Volunteer) {
    fun execute(database: Database, onFailure: (kotlin.Error) -> Unit, onComplete: (Volunteer) -> Unit) {
        if (database is FirebaseDatabaseImpl) {
            var id = ""
            if (volunteer.id.isEmpty()) {
                id = database.firebaseDb.reference.child("volunteers").push().key
            }

            database.firebaseDb.reference.child("volunteers").child(id).setValue(volunteer)
                    .addOnFailureListener {
                        onFailure(Error(it.localizedMessage))
                    }
                    .addOnCompleteListener {
                        onComplete(volunteer)
                    }
        }
    }
}