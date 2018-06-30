package guideme.volunteers.helpers.cache

interface LocalDataCache {
    fun getString(name: String): String?
}