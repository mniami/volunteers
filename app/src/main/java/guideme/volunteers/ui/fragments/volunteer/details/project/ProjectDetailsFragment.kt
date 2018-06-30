package guideme.volunteers.ui.fragments.volunteer.details.project

import guideme.volunteers.R
import guideme.volunteers.domain.ImageMetadata
import guideme.volunteers.domain.Project
import guideme.volunteers.ui.fragments.base.BaseFragment
import guideme.volunteers.ui.fragments.base.FragmentConfiguration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TableRow
import kotlinx.android.synthetic.main.project_details_fragment.*

class ProjectDetailsFragment : BaseFragment<ProjectDetailsPresenter>(), IProjectDetailsFragment {
    companion object {
        val PROJECT_ARG = "project"
    }
    init {
        presenter = ProjectDetailsPresenter(this)
        configuration = FragmentConfiguration.withLayout(R.layout.project_details_fragment).create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.let { updateView() }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter?.project = args?.get(PROJECT_ARG) as Project
    }

    private fun updateView() {
        val context = this.context

        if (context == null){
            return;
        }
        actionBar.setTitle(context.getString(R.string.project_label))
        presenter?.project?.let { project ->
            projectName?.text = project.name
            projectDescription?.text = project.description

            viewPager?.let {
                it.adapter = object : FragmentPagerAdapter(childFragmentManager) {
                    override fun getCount(): Int {
                        return 2
                    }

                    override fun getItem(position: Int): Fragment? {
                        var fragment: Fragment? = null
                        when (position) {
                            0 -> fragment = ProjectDetailsInfoFragment()
                            1 -> fragment = ProjectDetailsVolunteersFragment()
                        }
                        val bundle = Bundle()
                        bundle.putSerializable("project", project)
                        fragment?.arguments = bundle
                        return fragment
                    }

                    override fun getPageTitle(position: Int): CharSequence {
                        when (position) {
                            0 -> return "Info"
                            1 -> return "Volunteers"
                        }
                        return ""
                    }
                }
                slidingTabLayout?.setViewPager(it)
            }
            layoutInflater?.let { inflater ->
                if (project.images.size == 0) {
                    projectImages?.visibility = GONE
                }

                var adapter = ProjectImageAdapter(context, inflater) { view, imageMetadata ->
                    showImage(view, imageMetadata)
                }

                projectImages?.let { projectImages ->
                    var tableRow = TableRow(context)
                    var index = 0
                    val rowParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
                    tableRow.layoutParams = rowParams
                    projectImages.addView(tableRow)

                    project.images.forEach {
                        if (index > 2) {
                            index = 0
                            tableRow = TableRow(context)
                            tableRow.layoutParams = rowParams
                            projectImages.addView(tableRow)
                        }

                        adapter.instantiateItem(tableRow, it)
                        index++
                    }
                }
            }
        }
    }

    private fun showImage(view: View, imageMetadata: ImageMetadata) {
        //todo handle image metadata

        val imageView = ImageView(context)
        val oldImageView = view as ImageView

        oldImageView.let {
            imageView.setImageBitmap(oldImageView.drawingCache)
        }
        val layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        mainContainer?.addView(imageView, layoutParams)

        imageView.animate().scaleX(400f).scaleY(400f).translationX(400f).translationY(400f).start()
    }
}

