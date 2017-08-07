package android.benchmark.helpers.content

import android.benchmark.R
import android.content.Context

class AndroidResourceManager (val context : Context): ResourceManager {
    override fun getStringArray(id: Resources.Array): Array<String> {
        var resourceId = 0
        when (id){
            Resources.Array.TravelDestinations -> resourceId = R.array.travel_destinations
        }
        val typedArray  = context.resources.getStringArray(resourceId)
        return typedArray
    }

    override fun getString(id: Resources): String {
        when(id){
            Resources.ProjectLongDescription -> return context.getString(R.string.long_description)
            Resources.FacebookId -> return context.getString(R.string.facebook_app_id)
        }
    }
}