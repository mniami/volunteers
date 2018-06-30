package guideme.volunteers.ui.fragments.volunteer.details

import guideme.volunteers.domain.Volunteer
import guideme.volunteers.ui.activities.main.fragments.FragmentChangerImpl
import guideme.volunteers.ui.fragments.genericlist.GenericItem
import guideme.volunteers.ui.fragments.genericlist.GenericItemImpl
import guideme.volunteers.ui.fragments.genericlist.GenericItemMap
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

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
            return Observable.create { outer ->
                obs.subscribeBy(
                        onNext = {
                            it.person.let {
                                outer.onNext(GenericItemImpl(it.name + " " + it.surname, it.shortDescription, it.avatarImageUri, it))
                            }
                        },
                        onComplete = {
                            outer.onComplete()
                        })
            }
        }
        return null
    }
}
