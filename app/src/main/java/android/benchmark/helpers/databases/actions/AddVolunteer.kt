package android.benchmark.helpers.databases.actions

import android.benchmark.domain.Volunteer
import android.benchmark.helpers.databases.FirebaseDatabaseImpl
import android.benchmark.helpers.dataservices.databases.Database

class AddVolunteer(private val volunteer: Volunteer) {
    fun execute(database: Database, onFailure: (kotlin.Error) -> Unit, onComplete: (Volunteer) -> Unit) {
        if (database is FirebaseDatabaseImpl) {
            var newVolunteer = volunteer
            if (volunteer.id.isEmpty()) {
                val key = database.firebaseDb.reference.child("volunteers").push().key
                newVolunteer = volunteer.copy(key, volunteer.needies, volunteer.volunteerType, volunteer.person)
            }

            database.firebaseDb.reference.child(newVolunteer.id).setValue(newVolunteer)
                    .addOnFailureListener {
                        onFailure(Error(it.localizedMessage))
                    }
                    .addOnCompleteListener {
                        onComplete(newVolunteer)
                    }
        }
    }
}