package android.benchmark.helpers.cache

interface LocalDataCache {
    fun getString(name: String): String?
}