package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.databases.Database

class VolunteersDataSource(private val database: Database) : ObservableDataSource<Volunteer> {
    companion object {
        val ID = KeyDataSourceId("volunteers")
    }

    override val id: DataSourceId
        get() = ID

    override val data: ObservableData<Volunteer>
        get() {
            return ObservableDataImpl(database.getVolunteers())
        }
}