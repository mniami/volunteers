package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.genericlist.GenericItem
import android.benchmark.ui.fragments.genericlist.GenericItemImpl
import android.benchmark.ui.fragments.genericlist.GenericItemMap
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VolunteerGenericItemMap : GenericItemMap {
    override fun map(observable: Observable<*>): Observable<GenericItem<*>>? {
        val obs = observable as Observable<Volunteer>?
        if (obs != null) {
            return obs.observeOn(Schedulers.newThread())
                    .subscribeOn(Schedulers.newThread())
                    .map({
                        GenericItemImpl(it.name + " " + it.surname, it.shortDescription, it.avatarImageUri, it)
                    })
        }
        return null
    }
}
