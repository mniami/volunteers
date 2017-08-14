package android.benchmark.helpers.dataservices

import android.benchmark.helpers.dataservices.datasource.DataSource
import android.benchmark.helpers.dataservices.datasource.DataSourceId
import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.cache.LocalDataCache
import android.benchmark.helpers.dataservices.databases.Database
import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class DataServiceImpl(val database: Database,
                      val localDataCache: LocalDataCache,
                      val dataSourceContainer : DataSourceContainer) : DataService {
    override fun getDataSource(id: DataSourceId): DataSource {
        return dataSourceContainer.getDataSource(id)
    }

    override fun getVolunteers(): Observable<List<Volunteer>> {
        return Observable.create { emitter: ObservableEmitter<List<Volunteer>> ->
            val volunteers = arrayListOf<Volunteer>()
            volunteers.add(Volunteer(name = "Damian"))
            emitter.onNext(volunteers)
        }
    }

    override fun getUser(): Observable<User> {
        val userName = localDataCache.getString("user.name")
        if (userName != null) {
            return database.getUser(userName)
        }
        return Observable.empty<User>()
    }
}
