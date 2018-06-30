package guideme.volunteers.ui.activities.main.fragments

import guideme.volunteers.domain.Project
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class EmptyFragmentChanger(override var paused: Boolean = false,
                           override var supportFragmentManager: FragmentManager? = null) : FragmentChanger {
    override fun showError(errorMessage: ErrorMessage) {

    }

    override fun changeFragment(fragment: Fragment, name: String) {
    }

    override fun openSettings() {
    }

    override fun openAuthentication() {
    }

    override fun showVolunteerList() {
    }

    override fun openHome() {
    }

    override fun showProject(project: Project) {
    }

    override fun showVolunteer(volunteer: Volunteer) {
    }

    override fun openEditUserDetails(volunteer: Volunteer) {
    }
}