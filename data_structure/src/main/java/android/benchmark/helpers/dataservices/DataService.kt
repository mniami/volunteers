package android.benchmark.helpers.dataservices

import android.benchmark.helpers.dataservices.datasource.DataSource
import android.benchmark.helpers.dataservices.datasource.DataSourceId
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import io.reactivex.Observable

interface DataService {
    fun getVolunteers(): Observable<List<Volunteer>>
    fun getUser(): Observable<User>
    fun getDataSource(id : DataSourceId) : DataSource
}