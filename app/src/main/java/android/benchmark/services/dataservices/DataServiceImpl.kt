package android.benchmark.services.dataservices

import android.benchmark.domain.User
import android.benchmark.domain.Volunteer
import android.benchmark.services.cache.LocalDataCache
import android.benchmark.services.databases.Database
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

class DataServiceImpl(val database: Database, val localDataCache: LocalDataCache) : DataService {
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
class EmptyDataService : DataService {
    override fun getVolunteers(): Observable<List<Volunteer>> {
        return Observable.empty()
    }

    override fun getUser(): Observable<User> {
        return Observable.empty()
    }
}
