package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.volunteer.details.IVolunteerDetailsFragment

class VolunteerDetailsPresenter (val fragment: IVolunteerDetailsFragment){
    var volunteer : Volunteer? = null
}