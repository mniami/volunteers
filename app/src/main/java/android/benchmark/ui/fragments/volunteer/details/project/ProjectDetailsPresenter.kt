package android.benchmark.ui.fragments.volunteer.details.project

import android.benchmark.domain.Project
import android.benchmark.ui.fragments.base.Presenter

class ProjectDetailsPresenter(val fragment: ProjectDetailsFragment) : Presenter() {
    var project: Project? = null
}