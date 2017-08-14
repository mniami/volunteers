package android.benchmark.helpers.dataservices

import android.benchmark.helpers.dataservices.datasource.DataSource
import android.benchmark.helpers.dataservices.datasource.DataSourceId
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.datasource.EmptyDataSource
import io.reactivex.Observable

class EmptyDataService : DataService {
    override fun getDataSource(id: DataSourceId): DataSource {
        return EmptyDataSource(id)
    }

    override fun getVolunteers(): Observable<List<Volunteer>> {
        return Observable.empty()
    }

    override fun getUser(): Observable<User> {
        return Observable.empty()
    }
}