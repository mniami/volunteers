package android.benchmark.ui.fragments.genericlist

import android.benchmark.ui.fragments.base.Presenter

class GenericPresenter(val genericListFragment: GenericListFragment) : Presenter() {
    var genericListProvider : GenericListProvider? = null
}
