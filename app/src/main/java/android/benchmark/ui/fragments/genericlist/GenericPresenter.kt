package android.benchmark.ui.fragments.genericlist

import android.benchmark.ui.fragments.base.Presenter
import io.reactivex.Observable

class GenericPresenter(val genericListFragment: GenericListFragment) : Presenter() {
    var items: Observable<GenericItem<*>>? = null
}
