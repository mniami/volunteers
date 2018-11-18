package guideme.volunteers.helpers.content

import android.content.Context
import guideme.volunteers.R

class AndroidResourceManager (val context : Context): ResourceManager {
    override fun getStringArray(id: Resources.Array): Array<String> {
        var resourceId = 0
        when (id){
            Resources.Array.TravelDestinations -> resourceId = R.array.travel_destinations
        }
        return context.resources.getStringArray(resourceId)
    }

    override fun getString(id: Resources): String {
        return when (id) {
            Resources.ProjectLongDescription -> context.getString(R.string.long_description)
            Resources.FacebookId -> context.getString(R.string.facebook_application_id)
        }
    }
}