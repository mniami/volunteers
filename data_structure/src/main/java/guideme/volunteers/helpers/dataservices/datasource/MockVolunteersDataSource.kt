package guideme.volunteers.helpers.dataservices.datasource

import guideme.volunteers.domain.Volunteer
import guideme.volunteers.log.createLog
import io.reactivex.Observable
import io.reactivex.Single

class MockVolunteersDataSource : VolunteerDataSource {
    private val log = createLog(this)

    override fun update(data: Volunteer): Single<Volunteer> {
        return Single.create { emitter ->
            val index = volunteers.indexOfFirst { it.id == data.id }
            if (index >= 0) {
                volunteers[index] = data
                log.d { "update volunteer '${data.person.name}'" }

                emitter.onSuccess(data)
            } else {
                log.d { "add new volunteer '${data.person.name}'" }

                volunteers.add(data)
                emitter.onSuccess(data)
            }
            item.observable.publish()
        }
    }

    companion object {
        var volunteers: MutableList<Volunteer> = mutableListOf()
    }

    override val item: ObservableData<Volunteer> = ObservableDataImpl(Observable.create {
        val iterator = volunteers.iterator()
        while (iterator.hasNext()) {
            it.onNext(iterator.next())
        }
        it.onComplete()
    })
    override val id: DataSourceId
        get() = VolunteerDataSource.ID
}