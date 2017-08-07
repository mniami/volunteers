package android.benchmark.helpers.cache

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class AndroidLocalDataCache(val context: Context) : LocalDataCache {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("local", MODE_PRIVATE)

    override fun getString(name: String): String? {
        return sharedPreferences.getString(name, null)
    }
}