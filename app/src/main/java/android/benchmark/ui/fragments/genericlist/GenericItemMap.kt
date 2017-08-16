package android.benchmark.ui.fragments.genericlist

import io.reactivex.Observable

interface GenericItemMap {
    fun map (observable: Observable<*>) : Observable<GenericItem>?
}