package guideme.volunteers.ui.fragments.volunteer.details.project

import guideme.volunteers.domain.Project
import guideme.volunteers.ui.fragments.base.BasicPresenter

class ProjectDetailsPresenter(val fragment: IProjectDetailsFragment) : BasicPresenter() {
    var project: Project? = null
}