package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.Presenter
import android.benchmark.ui.fragments.volunteer.details.IVolunteerDetailsFragment

class VolunteerDetailsPresenter(val fragment: IVolunteerDetailsFragment) : Presenter(){
    var volunteer : Volunteer? = null
    set(value) {
        field = value
        value?.let {
            fragment.onVolunteerLoaded(value)
        }
    }
}