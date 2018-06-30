package guideme.volunteers.ui.fragments.volunteer.details.users

import guideme.volunteers.domain.Volunteer
import guideme.volunteers.ui.fragments.base.BasicPresenter

class VolunteerDetailsPresenter(val fragment: IVolunteerDetailsFragment) : BasicPresenter(){
    var volunteer : Volunteer? = null
}