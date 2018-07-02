package guideme.volunteers.helpers.dataservices.datasource

import guideme.volunteers.domain.Volunteer
import guideme.volunteers.log.createLog
import io.reactivex.Observable
import io.reactivex.Single

class MockVolunteersDataSource : VolunteerDataSource {
    private val log = createLog(this)

    override fun update(volunteer: Volunteer): Single<Volunteer> {
        return Single.create {
            val index = volunteers.indexOfFirst { it.id == volunteer.id }
            if (index >= 0) {
                volunteers[index] = volunteer
                log.d { "update volunteer '${volunteer.person.name}'" }

                it.onSuccess(volunteer)
            } else {
                log.d { "add new volunteer '${volunteer.person.name}'" }

                volunteers.add(volunteer)
                it.onSuccess(volunteer)
            }
            data.observable.publish()
        }
    }

    companion object {
        var volunteers: MutableList<Volunteer> = mutableListOf()
    }

    override val data: ObservableData<Volunteer> = ObservableDataImpl(Observable.create {
        val iterator = volunteers.iterator()
        while (iterator.hasNext()) {
            it.onNext(iterator.next())
        }
        it.onComplete()
    })
    override val id: DataSourceId
        get() = VolunteerDataSource.ID
}