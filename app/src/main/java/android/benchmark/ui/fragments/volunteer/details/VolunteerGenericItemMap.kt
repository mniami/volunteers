package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Volunteer
import android.benchmark.ui.activities.main.FragmentChangerImpl
import android.benchmark.ui.fragments.genericlist.GenericItem
import android.benchmark.ui.fragments.genericlist.GenericItemImpl
import android.benchmark.ui.fragments.genericlist.GenericItemMap
import io.reactivex.Observable

class VolunteerGenericItemMap(val fragmentChanger: FragmentChangerImpl) : GenericItemMap {
    override fun addItem() {
        val volunteer = Volunteer()
        fragmentChanger.openEditUserDetails(volunteer)
    }

    override fun removeItem(items: List<GenericItem<*>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun map(observable: Observable<*>): Observable<GenericItem<*>>? {
        val obs = observable as Observable<Volunteer>?
        if (obs != null) {
            return obs.map({
                        GenericItemImpl(it.name + " " + it.surname, it.shortDescription, it.avatarImageUri, it)
                    })
        }
        return null
    }
}
