package android.benchmark.ui.fragments.genericlist

import android.benchmark.helpers.dataservices.datasource.Data
import android.benchmark.helpers.dataservices.datasource.DataSource
import android.benchmark.helpers.dataservices.datasource.DataSourceId
import android.benchmark.helpers.dataservices.datasource.ListData

class GenericListDataSource(override val id: DataSourceId, override val list : List<GenericItem>)
    : DataSource, GenericListProvider{
    override val data: Data
        get() = ListData<GenericItem>(list)
}