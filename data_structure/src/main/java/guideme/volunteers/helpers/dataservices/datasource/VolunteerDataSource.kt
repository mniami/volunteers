package guideme.volunteers.helpers.dataservices.datasource

import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.databases.Database
import io.reactivex.Single

interface VolunteerDataSource : ModifiableDataSource<Volunteer> {
    companion object {
        val ID = KeyDataSourceId("volunteers")
    }
}

class VolunteerDataSourceImpl(private val database: Database) : VolunteerDataSource {
    override val id: DataSourceId
        get() = VolunteerDataSource.ID

    override val item: ObservableData<Volunteer>
        get() = ObservableDataImpl(database.getVolunteers())

    override fun update(data: Volunteer): Single<Volunteer> =
            database.updateVolunteer(data)
}