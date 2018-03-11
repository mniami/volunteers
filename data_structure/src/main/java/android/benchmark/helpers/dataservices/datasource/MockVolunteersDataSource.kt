package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.Volunteer
import android.benchmark.log.createLog
import io.reactivex.Observable

class MockVolunteersDataSource : VolunteerDataSource {
    private val log = createLog(this.javaClass.kotlin)

    override fun update(volunteer: Volunteer): Observable<Volunteer> {
        return Observable.create {
            val index = volunteers.indexOfFirst { it.id == volunteer.id }
            if (index >= 0) {
                volunteers[index] = volunteer
                log.d { "update volunteer '${volunteer.person.name}'" }

                it.onNext(volunteer)
                it.onComplete()
            } else {
                log.d { "add new volunteer '${volunteer.person.name}'" }

                volunteers.add(volunteer)
                it.onNext(volunteer)
                it.onComplete()
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