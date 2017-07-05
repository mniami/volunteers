package android.benchmark.services.content

import android.benchmark.R
import android.content.Context

class AndroidResourceManager (val context : Context): ResourceManager{
    override fun getString(id: Resources): String {
        when(id){
            Resources.ProjectLongDescription -> return context.getString(R.string.long_description)
            Resources.FacebookId -> return context.getString(R.string.facebook_app_id)
        }
    }
}