package android.benchmark.ui.fragments.genericlist

import android.benchmark.helpers.dataservices.datasource.DataSource

fun DataSource.isGenericListDataSource() : Boolean{
    return this is GenericListDataSource
}
fun <T> DataSource.asGenericListDataSource() : GenericListDataSource {
    return this as GenericListDataSource
}
