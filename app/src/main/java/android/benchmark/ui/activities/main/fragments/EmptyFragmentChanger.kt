package android.benchmark.ui.activities.main.fragments

import android.benchmark.domain.Person
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.errors.ErrorMessage
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

    override fun openEditUserDetails(person: Person) {
    }
}