package guideme.volunteers.ui.fragments.genericlist

import guideme.volunteers.helpers.dataservices.datasource.DataSource
import guideme.volunteers.helpers.dataservices.datasource.ObservableDataSource

fun DataSource.isObservableDataSource(): Boolean {
    return this is ObservableDataSource<*>
}
