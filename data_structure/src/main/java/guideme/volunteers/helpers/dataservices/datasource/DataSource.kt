package guideme.volunteers.helpers.dataservices.datasource

import io.reactivex.Observable
import java.io.Serializable
import java.util.*

interface Data : Serializable

open class DataSourceId : Serializable

class KeyDataSourceId(val key : String) : DataSourceId() {
    override fun equals(other: Any?): Boolean {
        return key.equals(other)
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}

class EmptyData: Data

abstract class ObservableData<T>: Data{
    abstract val observable : Observable<T>
}

interface DataSource {
    val id : DataSourceId
    val data : Data
}

interface ObservableDataSource<T>: DataSource{
    override val id: DataSourceId
    override val data: ObservableData<T>
}
interface ModifiableDataSource<T> : ObservableDataSource<T>{
    fun update(data : T) : Observable<T>
}
class ObservableDataImpl<T>(override val observable: Observable<T>) : ObservableData<T>()

class EmptyDataSource(override val id: DataSourceId = DataSourceId(), override val data: Data = EmptyData()) : DataSource

interface DataSourceContainer{
    fun getDataSource(dataSourceId: DataSourceId): DataSource?
    fun putDataSource(dataSource: DataSource)
}

class DataSourceContainerImpl : DataSourceContainer {
    val map = HashMap<DataSourceId, DataSource>()

    override fun putDataSource(dataSource: DataSource) {
        map.put(dataSource.id, dataSource)
    }

    override fun getDataSource(dataSourceId : DataSourceId) : DataSource? {
        return map.get(dataSourceId)
    }
}

