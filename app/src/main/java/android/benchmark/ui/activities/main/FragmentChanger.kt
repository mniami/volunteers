package android.benchmark.ui.activities.main

import android.androidkotlinbenchmark.R
import android.benchmark.domain.Person
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.ui.fragments.VolunteersFragmentPresenter
import android.benchmark.ui.fragments.settings.AuthenticationFragmentImpl
import android.benchmark.ui.fragments.settings.SettingsFragment
import android.benchmark.ui.fragments.volunteer.details.VolunteerDetailsFragment
import android.benchmark.ui.fragments.volunteer.details.admin.AdminUserDetailsFragment
import android.benchmark.ui.fragments.volunteer.details.project.ProjectDetailsFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class FragmentChanger (val supportFragmentManager: FragmentManager, val dataSourceContainer: DataSourceContainer, var paused : Boolean = false){

    fun changeFragment(fragment: Fragment, name: String) {
//        if (paused){
//            return
//        }
        supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragmentContainer, fragment, name)
                .commit()
    }
    fun openSettings() = changeFragment(SettingsFragment(), FragmentNames.SETTINGS)
    fun openAuthentication() = changeFragment(AuthenticationFragmentImpl(), FragmentNames.AUTHENTICATION)
    fun showVolunteerList() = changeFragment(VolunteersFragmentPresenter().createFragment(dataSourceContainer) { showVolunteer(it) }, FragmentNames.VOLUNTEERS_LIST)

    fun openHome() {
        if (paused){
            return
        }
        supportFragmentManager.popBackStack(FragmentNames.VOLUNTEERS_LIST, R.id.fragmentContainer)
    }
    fun showProject(project: Project) {
        val bundle = Bundle()
        bundle.putSerializable(ProjectDetailsFragment.PROJECT_ARG, project)

        val projectDetailsFragment = ProjectDetailsFragment()
        projectDetailsFragment.arguments = bundle

        changeFragment(projectDetailsFragment, FragmentNames.PROJECT)
    }
    fun showVolunteer(volunteer: Volunteer) {
        val bundle = Bundle()
        bundle.putSerializable(VolunteerDetailsFragment.VOLUNTEER_ARG, volunteer)

        val volunteerDetailsFragment = VolunteerDetailsFragment()
        volunteerDetailsFragment.arguments = bundle

        changeFragment(volunteerDetailsFragment, FragmentNames.VOLUNTEER)
    }

    fun openEditUserDetails(person: Person) {
        val bundle = Bundle()
        bundle.putSerializable(AdminUserDetailsFragment.PERSON_ARG, person)

        val fragment = AdminUserDetailsFragment()
        fragment.arguments = bundle

        changeFragment(fragment, FragmentNames.EDIT_PERSON)
    }
}