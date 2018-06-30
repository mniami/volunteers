package guideme.volunteers.ui.fragments.volunteer.details.users

import guideme.volunteers.domain.Person
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.ui.fragments.base.BasicPresenter

class EditableUserPresenter(var volunteer: Volunteer? = null) : BasicPresenter() {
    var person: Person?
        get() = volunteer?.person
        private set(_) {}

    override fun onPause() {
        super.onPause()
    }
}