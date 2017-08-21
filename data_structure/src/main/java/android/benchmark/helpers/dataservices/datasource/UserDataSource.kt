package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.User
import android.benchmark.helpers.cache.LocalDataCache
import android.benchmark.helpers.dataservices.databases.Database
import io.reactivex.Observable

class UserDataSource(val database: Database, val localDataCache : LocalDataCache) : ObservableDataSource<User>{
    companion object {
        val ID = KeyDataSourceId("current.user.name")
    }

    override val data: ObservableData<User>
        get() {
            val userName = localDataCache.getString("current.user.name")
            if (userName != null) {
                return ObservableDataImpl(database.getUser(userName))
            }
            return ObservableDataImpl(Observable.empty<User>())
        }
    override val id: DataSourceId
        get() {
            return ID
        }
}