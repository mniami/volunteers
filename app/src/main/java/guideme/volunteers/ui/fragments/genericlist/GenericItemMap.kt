package guideme.volunteers.ui.fragments.genericlist

import io.reactivex.Observable

interface GenericItemMap {
    fun map (observable: Observable<*>) : Observable<GenericItem<*>>?
    fun addItem()
    fun removeItem(items : List<GenericItem<*>>)
}
class EmptyGenericItemMap : GenericItemMap {
    override fun map(observable: Observable<*>): Observable<GenericItem<*>>? {
        return Observable.create {}
    }

    override fun addItem() {
        // nth to do
    }

    override fun removeItem(items: List<GenericItem<*>>) {
        // nth to do
    }
}