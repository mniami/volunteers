package android.benchmark.services.cache

interface ILocalDataCache {
    fun getString(name: String): String?
}