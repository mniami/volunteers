package android.benchmark.ui.fragments.volunteer.details.presenters

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.Presenter
import android.benchmark.ui.fragments.volunteer.details.IVolunteerDetailsFragment

class VolunteerDetailsPresenter(val fragment: IVolunteerDetailsFragment) : Presenter(){
    var volunteer : Volunteer? = null
}