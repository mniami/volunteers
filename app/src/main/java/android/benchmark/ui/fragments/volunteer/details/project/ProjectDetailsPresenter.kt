package android.benchmark.ui.fragments.volunteer.details.project

import android.benchmark.domain.Project
import android.benchmark.ui.fragments.base.BasicPresenter

class ProjectDetailsPresenter(val fragment: IProjectDetailsFragment) : BasicPresenter() {
    var project: Project? = null
}