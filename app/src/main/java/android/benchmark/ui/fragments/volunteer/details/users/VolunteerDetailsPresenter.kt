package android.benchmark.ui.fragments.volunteer.details.users

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BasicPresenter

class VolunteerDetailsPresenter(val fragment: IVolunteerDetailsFragment) : BasicPresenter(){
    var volunteer : Volunteer? = null
}