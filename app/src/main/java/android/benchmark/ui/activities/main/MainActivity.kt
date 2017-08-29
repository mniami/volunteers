package android.benchmark.ui.activities.main

import android.benchmark.auth.SignInAuthResult
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.ui.views.actionbar.ActionBarTool

interface MainActivity {
    val actionBarTool: ActionBarTool
    fun openSettings()
    fun getResourceText(id: Int): String
    fun goBack()
    fun openHome()
    fun showVolunteer(volunteer: Volunteer)
    fun openAuthentication()
    fun showVolunteerList()
    fun showProject(project: Project)
}

interface MainView : MainActivity {
    fun updateUserStatus(signInResult: SignInAuthResult)
}