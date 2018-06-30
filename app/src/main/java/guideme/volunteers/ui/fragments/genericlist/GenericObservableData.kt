package guideme.volunteers.ui.fragments.genericlist

import guideme.volunteers.helpers.dataservices.datasource.Data
import io.reactivex.Observable

class GenericObservableData(override val observable: Observable<GenericItem<*>>) : Data, GenericObservableProvider