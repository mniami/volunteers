package android.benchmark.ui.activities.main

import android.benchmark.domain.Person
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
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
    fun openEditUserDetails(person: Person)
}