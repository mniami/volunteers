package android.benchmark.helpers.dataservices.datasource

import android.benchmark.domain.Action
import android.benchmark.domain.Privilege
import android.benchmark.domain.User
import io.reactivex.Observable

class MockUserDataSource(override val id: DataSourceId = KeyDataSourceId("current.user.name")) : UserDataSource{
    override fun update(user: User): Observable<User> {
        return Observable.create {
            user.activity.actions.add(Action("User updated"))
            data.observable.publish()
            it.onNext(user)
            it.onComplete()
        }
    }

    override val data: ObservableData<User> = ObservableDataImpl(Observable.create {
            it.onNext(user)
            it.onComplete()
        })

    companion object {
        val user = User(name = "Janek", surname = "Kowalski", privilege = Privilege.ADMIN, email = "janek@u.janka.pl")
    }
}