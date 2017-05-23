package android.benchmark.ui.activities.main

import android.benchmark.domain.Volunteer
import android.benchmark.ui.fragments.base.IFragmentContainer
import android.benchmark.ui.views.actionbar.IActionBarTool

interface IMainActivity {
    val actionBarTool: IActionBarTool
    fun navigateTo(fragmentContainer: IFragmentContainer)
    fun openSettings()
    fun getResourceText(id: Int): String
    fun goBack()
    fun showVolunteer(volunteer: Volunteer)
    fun openAuthentication()
}

