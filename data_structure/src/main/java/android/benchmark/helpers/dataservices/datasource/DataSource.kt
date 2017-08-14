package android.benchmark.helpers.dataservices.datasource

import java.io.Serializable

interface Data : Serializable
class ListData<T> (val list : List<T>): Data {

}
class EmptyData: Data
interface DataSource {
    val id : DataSourceId
    val data : Data
}
class EmptyDataSource(override val id: DataSourceId = DataSourceId(), override val data: Data = EmptyData()) : DataSource
interface DataSourceContainer{
    fun getDataSource(dataSourceId: DataSourceId): DataSource
}
class DataSourceContainerImpl : DataSourceContainer {
    val map = HashMap<DataSourceId, DataSource>()

    override fun getDataSource(dataSourceId : DataSourceId) : DataSource {
        return map.get(dataSourceId) as DataSource
    }
}

class DataSourceId : Serializable
