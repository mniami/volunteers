package guideme.volunteers.helpers.dataservices.datasource

import guideme.volunteers.auth.Auth
import guideme.volunteers.domain.User
import guideme.volunteers.helpers.dataservices.databases.Database
import io.reactivex.Single

interface UserDataSource : ModifiableDataSource<User> {
    companion object {
        val ID = KeyDataSourceId("current.user.name")
    }
}

class UserDataSourceImpl(private val database: Database, private val auth: Auth) : UserDataSource {
    override val item: ObservableData<User>
        get() = ObservableDataImpl(database.getCurrentUser().toObservable())
    override val id: DataSourceId
        get() = UserDataSource.ID

    override fun update(user: User): Single<User> = database.setUser(user)
}