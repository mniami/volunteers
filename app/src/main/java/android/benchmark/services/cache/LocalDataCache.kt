package android.benchmark.services.cache

interface LocalDataCache {
    fun getString(name: String): String?
}