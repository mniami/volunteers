package guideme.volunteers.ui.activities.main

import guideme.volunteers.domain.Human
import guideme.volunteers.domain.Project
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.ui.activities.main.base.BaseMainActivity

interface MainActivity : BaseMainActivity {
    fun openSettings()
    fun getResourceText(id: Int): String
    fun showVolunteer(volunteer: Volunteer)
    fun openAuthentication()
    fun showVolunteerList()
    fun showProject(project: Project)
    fun openEditUserDetails(human: Human)
    fun showError(errorMessage: ErrorMessage)
}
