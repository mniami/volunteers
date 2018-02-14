package android.benchmark.ui.fragments.volunteer.details.users

import android.benchmark.domain.Person
import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.BasicPresenter

class EditableUserPresenter(var volunteer: Volunteer? = null) : BasicPresenter() {
    var person: Person?
        get() = volunteer?.person
        private set(_) {}

    override fun onPause() {
        super.onPause()
    }
}