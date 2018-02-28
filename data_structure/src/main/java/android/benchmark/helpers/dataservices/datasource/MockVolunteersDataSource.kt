package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.Volunteer
import io.reactivex.Observable

class MockVolunteersDataSource : VolunteerDataSource {
    override fun update(volunteer: Volunteer): Observable<Volunteer> {
        return Observable.create {
            val index = volunteers.indexOfFirst { it.key == volunteer.key }
            if (index >= 0) {
                volunteers[index] = volunteer
                it.onNext(volunteer)
                it.onComplete()
            } else {
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