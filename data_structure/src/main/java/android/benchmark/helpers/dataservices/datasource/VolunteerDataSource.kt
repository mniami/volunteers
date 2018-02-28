package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.databases.Database
import io.reactivex.Observable

interface VolunteerDataSource : ModifiableDataSource<Volunteer>{
    companion object {
        val ID = KeyDataSourceId("volunteers")
    }
}

class VolunteerDataSourceImpl(private val database: Database) : VolunteerDataSource {
    override val id: DataSourceId
        get() = VolunteerDataSource.ID

    override val data: ObservableData<Volunteer>
        get() {
            return ObservableDataImpl(database.getVolunteers())
        }

    override fun update(volunteer : Volunteer) : Observable<Volunteer> =
            database.updateVolunteer(volunteer)
}