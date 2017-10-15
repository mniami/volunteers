package android.benchmark.ui.fragments.genericlist

class MapperInstanceProvider (private val mappers : MutableMap<Any, GenericItemMap> = mutableMapOf()) {
    fun register(key : Any, itemMap : GenericItemMap) {
        mappers.put(key, itemMap)
    }
    fun <T> get(key : Any) : T? where T:GenericItemMap{
        return mappers[key] as T?
    }
}