package guideme.volunteers.ui.fragments.volunteer.details

import guideme.volunteers.domain.Volunteer
import guideme.volunteers.ui.fragments.base.BasicPresenter

class VolunteerProjectListPresenter(val view : IVolunteerProjectListFragment) : BasicPresenter(){
    var volunteer = Volunteer()

    fun onViewCreated() {
    }
}