package android.benchmark.ui.fragments.volunteer.details.users

import android.benchmark.domain.Person
import android.benchmark.ui.fragments.base.BasicPresenter

class EditableUserPresenter(private var originalPerson: Person? = null) : BasicPresenter() {
    var person: Person? = null
        set(value) {
            originalPerson = value
            field = value
        }

    override fun onPause() {
        super.onPause()
        updateChanges()
    }

    private fun updateChanges() {
        val currentPerson = person
        val currentOriginalPerson = originalPerson

        if (currentPerson != currentOriginalPerson) {
            //volunteersDataSource.
        }
    }
}