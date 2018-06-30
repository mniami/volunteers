package guideme.volunteers.ui.activities.main.fragments

import guideme.volunteers.domain.Project
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

interface FragmentChanger {
    var paused : Boolean
    var supportFragmentManager: FragmentManager?
    fun changeFragment(fragment: Fragment, name: String)
    fun openSettings()
    fun openAuthentication()
    fun showVolunteerList()
    fun openHome()
    fun showProject(project: Project)
    fun showVolunteer(volunteer: Volunteer)
    fun openEditUserDetails(volunteer: Volunteer)
    fun showError(errorMessage: ErrorMessage)
}