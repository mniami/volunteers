package guideme.volunteers

import android.app.Application
import guideme.volunteers.DependencyModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DependencyModule().init(this)
    }
}