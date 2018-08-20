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

    override fun update(data: User): Single<User> {
        log.d { "update user called ${data.person.name}" }
        return Single.create {
            data.person.activity.actions.add(Action("User updated"))
            item.observable.publish()
            it.onSuccess(data)
        }
    }

    override val item: ObservableData<User> = ObservableDataImpl(Observable.create {
        log.d { "retrieved user data ${user.person.name}" }
        it.onNext(user)
        it.onComplete()
    })

    companion object {
        val user = User(person = Person(name = "Janek", surname = "Kowalski", privilege = Privilege.ADMIN, email = "janek@u.janka.pl"))
    }
}