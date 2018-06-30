package guideme.volunteers.ui.activities.main

import guideme.volunteers.domain.Project
import guideme.volunteers.domain.Volunteer
import guideme.volunteers.helpers.dataservices.errors.ErrorMessage
import guideme.volunteers.ui.views.actionbar.ActionBarTool

class EmptyMainActivity(override val actionBarTool: ActionBarTool) : MainActivity {
    override fun addListener(listener: MainActivityListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeListener(listener: MainActivityListener) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMessage: ErrorMessage) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openEditUserDetails(volunteer: Volunteer) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProject(project: Project) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openSettings() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getResourceText(id: Int): String {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return ""
    }

    override fun goBack() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openHome() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVolunteer(volunteer: Volunteer) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun openAuthentication() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showVolunteerList() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}