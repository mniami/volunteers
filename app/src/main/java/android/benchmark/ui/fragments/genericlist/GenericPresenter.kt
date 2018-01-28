package android.benchmark.ui.fragments.genericlist

import android.benchmark.ui.fragments.base.BasicPresenter
import io.reactivex.Observable

class GenericPresenter(val genericListFragment: GenericListFragment) : BasicPresenter() {
    var items: Observable<GenericItem<*>>? = null
}
