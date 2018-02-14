package android.benchmark.ui.activities.main

import android.benchmark.domain.Person
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.errors.ErrorMessage
import android.benchmark.ui.activities.main.base.BaseMainActivity

interface MainActivity : BaseMainActivity {
    fun openSettings()
    fun getResourceText(id: Int): String
    fun showVolunteer(volunteer: Volunteer)
    fun openAuthentication()
    fun showVolunteerList()
    fun showProject(project: Project)
    fun openEditUserDetails(volunteer: Volunteer)
    fun showError(errorMessage: ErrorMessage)
}
