package android.benchmark.ui.fragments.volunteer.details.project

import android.benchmark.R
import android.benchmark.domain.ImageMetadata
import android.benchmark.domain.Project
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TableRow
import kotlinx.android.synthetic.main.project_details_fragment.*

class ProjectDetailsFragment : Fragment(), IProjectDetailsFragment {
    private val presenter: ProjectDetailsPresenter = ProjectDetailsPresenter(this)
    private var layoutInflater : LayoutInflater? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        layoutInflater = inflater
        if (savedInstanceState == null) {
            return inflater!!.inflate(R.layout.project_details_fragment, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.let { v ->
            updateView()
        }
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        presenter.project = args?.get("project") as Project
    }

    private fun updateView() {
        presenter.project?.let { project ->
            tvDescription?.text = project.description
            layoutInflater?.let { inflater ->
                if (project.images.size === 0) {
                    vpImages?.visibility = GONE
                }

                var adapter = ProjectImageAdapter(context, inflater) { view, imageMetadata ->
                    showImage(view, imageMetadata)
                }

                vpImages?.let { vpImages ->
                    var tableRow = TableRow(context)
                    var index = 0
                    val rowParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT)
                    tableRow.setLayoutParams(rowParams)
                    vpImages.addView(tableRow)

                    project.images.forEach {
                        if (index > 2) {
                            index = 0
                            tableRow = TableRow(context)
                            tableRow.setLayoutParams(rowParams)
                            vpImages.addView(tableRow)
                        }

                        adapter.instantiateItem(tableRow, it)
                        index++
                    }
                }
            }
        }
    }

    private fun showImage(view: View, imageMetadata: ImageMetadata) {
        val imageView = ImageView(context)
        val oldImageView = view as ImageView

        oldImageView?.let {
            imageView.setImageBitmap(oldImageView.drawingCache)
        }
        val layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        mainContainer?.addView(imageView, layoutParams)

        imageView.animate().scaleX(400f).scaleY(400f).translationX(400f).translationY(400f).start()
    }
}

