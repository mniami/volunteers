package guideme.volunteers.ui.fragments.genericlist

import io.reactivex.Observable

interface GenericObservableProvider {
    val observable : Observable<GenericItem<*>>
}