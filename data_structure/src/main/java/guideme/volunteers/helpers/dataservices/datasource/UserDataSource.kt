package guideme.volunteers.helpers.dataservices.datasource

import guideme.volunteers.auth.Auth
import guideme.volunteers.domain.User
import guideme.volunteers.helpers.dataservices.databases.Database
import io.reactivex.Observable

interface UserDataSource : ModifiableDataSource<User> {
    companion object {
        val ID = KeyDataSourceId("current.user.name")
    }
}

class UserDataSourceImpl(private val database: Database, private val auth: Auth) : UserDataSource {
    override val data: ObservableData<User>
        get() {
            return ObservableDataImpl(database.getCurrentUserAsync())
        }
    override val id: DataSourceId
        get() {
            return UserDataSource.ID
        }

    override fun update(user: User): Observable<User> {
        return database.setUser(user)
    }
}