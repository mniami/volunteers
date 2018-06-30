package guideme.volunteers.ui.fragments.genericlist

import guideme.volunteers.ui.fragments.base.BasicPresenter
import io.reactivex.Observable

class GenericPresenter(val genericListFragment: GenericListFragment) : BasicPresenter() {
    var items: Observable<GenericItem<*>>? = null
}
