package guideme.volunteers.eventbus

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.io.Serializable
import java.util.*

class EventBusContainer {
    val map : MutableMap<Serializable, EventBus<*>> = HashMap()

    fun <T> get(key: Serializable): EventBus<T>? {
        return if (map.containsKey(key)) {
            val eventBus5 = map[key] as EventBus<*>
            return eventBus5 as EventBus<T>
        }
        else {
            val eventBus = EventBus<T>()
            map[key] = eventBus
            eventBus
        }
    }
}

class EventBus<T> @JvmOverloads constructor(private val subject: Subject<T> = PublishSubject.create<T>()) {

    fun <E : T> post(event: E) {
        subject.onNext(event)
    }
    fun observe(): Observable<T> = subject
}