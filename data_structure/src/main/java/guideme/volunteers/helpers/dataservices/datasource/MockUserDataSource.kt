package guideme.volunteers.helpers.dataservices.datasource

import guideme.volunteers.domain.Action
import guideme.volunteers.domain.Person
import guideme.volunteers.domain.Privilege
import guideme.volunteers.domain.User
import guideme.volunteers.log.createLog
import io.reactivex.Observable
import io.reactivex.Single

class MockUserDataSource(override val id: DataSourceId = KeyDataSourceId("current.user.name")) : UserDataSource {
    private val log = createLog(this)

    override fun update(user: User): Single<User> {
        log.d { "update user called ${user.person.name}" }
        return Single.create {
            user.person.activity.actions.add(Action("User updated"))
            data.observable.publish()
            it.onSuccess(user)
        }
    }

    override val data: ObservableData<User> = ObservableDataImpl(Observable.create {
        log.d { "retrieved user data ${user.person.name}" }
        it.onNext(user)
        it.onComplete()
    })

    companion object {
        val user = User(person = Person(name = "Janek", surname = "Kowalski", privilege = Privilege.ADMIN, email = "janek@u.janka.pl"))
    }
}