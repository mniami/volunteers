package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.Action
import android.benchmark.domain.Person
import android.benchmark.domain.Privilege
import android.benchmark.domain.User
import android.benchmark.log.createLog
import io.reactivex.Observable

class MockUserDataSource(override val id: DataSourceId = KeyDataSourceId("current.user.name")) : UserDataSource {
    private val log = createLog(this.javaClass.kotlin)

    override fun update(user: User): Observable<User> {
        log.d { "update user called ${user.person.name}" }
        return Observable.create {
            user.person.activity.actions.add(Action("User updated"))
            data.observable.publish()
            it.onNext(user)
            it.onComplete()
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