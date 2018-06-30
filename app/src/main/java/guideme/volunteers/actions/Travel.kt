package guideme.volunteers.actions

import guideme.volunteers.domain.Level
import guideme.volunteers.domain.TravelDestination
import guideme.volunteers.helpers.content.ResourceManager
import guideme.volunteers.helpers.content.Resources

class TravelDestinationResourceMapper(val resourceManager: ResourceManager) {
    fun map(): List<TravelDestination> {
        val res = resourceManager.getStringArray(Resources.Array.TravelDestinations)
        return res.map {
            it ->
            TravelDestination(it, Level(0f))
        }
    }
}