package android.benchmark.ui.activities.main.fragments

import android.androidkotlinbenchmark.R
import android.benchmark.domain.Project
import android.benchmark.domain.Volunteer
import android.benchmark.helpers.dataservices.errors.ErrorMessage
import android.benchmark.helpers.dataservices.datasource.DataSourceContainer
import android.benchmark.ui.activities.main.FragmentNames
import android.benchmark.ui.activities.main.base.BaseMainActivityImpl
import android.benchmark.ui.fragments.ErrorFragmentImpl
import android.benchmark.ui.fragments.VolunteersFragmentPresenter
import android.benchmark.ui.fragments.settings.AuthenticationFragmentImpl
import android.benchmark.ui.fragments.settings.SettingsFragment
import android.benchmark.ui.fragments.volunteer.details.users.VolunteerDetailsFragment
import android.benchmark.ui.fragments.volunteer.details.users.EditableUserDetailsFragment
import android.benchmark.ui.fragments.volunteer.details.project.ProjectDetailsFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class FragmentChangerImpl(override var supportFragmentManager: FragmentManager? = null,
                          override var paused: Boolean = false,
                          private val dataSourceContainer: DataSourceContainer) : FragmentChanger {

    override fun changeFragment(fragment: Fragment, name: String) {
        val isHome = supportFragmentManager?.backStackEntryCount == 0

        supportFragmentManager?.beginTransaction()
                ?.addToBackStack(if (isHome) BaseMainActivityImpl.HOME_FRAGMENT_STACK_NAME else null)
                ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                ?.replace(R.id.fragmentContainer, fragment, name)
                ?.commit()
    }

    override fun openSettings() = changeFragment(SettingsFragment(), FragmentNames.SETTINGS)
    override fun openAuthentication() = changeFragment(AuthenticationFragmentImpl(), FragmentNames.AUTHENTICATION)
    override fun showVolunteerList() = changeFragment(VolunteersFragmentPresenter().createFragment(dataSourceContainer) { showVolunteer(it) }, FragmentNames.VOLUNTEERS_LIST)
    override fun showError(errorMessage: ErrorMessage) = changeFragment(ErrorFragmentImpl.createFragment(errorMessage),
            FragmentNames.ERROR)

    override fun openHome() {
//        if (paused) {
//            return
//        }
        supportFragmentManager?.popBackStack(BaseMainActivityImpl.HOME_FRAGMENT_STACK_NAME, R.id.fragmentContainer)
    }

    override fun showProject(project: Project) {
        val bundle = Bundle()
        bundle.putSerializable(ProjectDetailsFragment.PROJECT_ARG, project)

        val projectDetailsFragment = ProjectDetailsFragment()
        projectDetailsFragment.arguments = bundle

        changeFragment(projectDetailsFragment, FragmentNames.PROJECT)
    }

    override fun showVolunteer(volunteer: Volunteer) {
        val bundle = Bundle()
        bundle.putSerializable(VolunteerDetailsFragment.VOLUNTEER_ARG, volunteer)

        val volunteerDetailsFragment = VolunteerDetailsFragment()
        volunteerDetailsFragment.arguments = bundle

        changeFragment(volunteerDetailsFragment, FragmentNames.VOLUNTEER)
    }

    override fun openEditUserDetails(volunteer: Volunteer) {
        val bundle = Bundle()
        bundle.putSerializable(EditableUserDetailsFragment.VOLUNTEER_ARG, volunteer)

        val fragment = EditableUserDetailsFragment()
        fragment.arguments = bundle

        changeFragment(fragment, FragmentNames.EDIT_PERSON)
    }
}