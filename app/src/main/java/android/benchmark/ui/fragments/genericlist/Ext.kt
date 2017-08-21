package android.benchmark.ui.fragments.genericlist

import android.benchmark.helpers.dataservices.datasource.DataSource
import android.benchmark.helpers.dataservices.datasource.ObservableDataSource

fun DataSource.isObservableDataSource() : Boolean{
    return this is ObservableDataSource<*>
}
fun <T> DataSource.asObservableDataSource() : ObservableDataSource<T>? {
    return this as ObservableDataSource<T>
}
