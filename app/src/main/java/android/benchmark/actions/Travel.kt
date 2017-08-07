package android.benchmark.actions

import android.benchmark.domain.Level
import android.benchmark.domain.TravelDestination
import android.benchmark.helpers.content.ResourceManager
import android.benchmark.helpers.content.Resources

class TravelDestinationResourceMapper(val resourceManager: ResourceManager) {
    fun map(): List<TravelDestination> {
        val res = resourceManager.getStringArray(Resources.Array.TravelDestinations)
        return res.map {
            it ->
            TravelDestination(it, Level(0f))
        }
    }
}