package android.benchmark.ui.fragments.genericlist

import android.benchmark.helpers.dataservices.datasource.Data
import io.reactivex.Observable

class GenericObservableData(override val observable: Observable<GenericItem<*>>) : Data, GenericObservableProvider