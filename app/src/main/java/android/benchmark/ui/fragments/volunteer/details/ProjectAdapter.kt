package android.benchmark.ui.fragments.volunteer.details

import android.benchmark.R
import android.benchmark.domain.Project
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProjectAdapter(val data: List<Project>, val onClickListener: (Project) -> Unit) :
        RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProjectAdapter.ViewHolder {
        return ProjectAdapter.ViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.volunteer_item, parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: ProjectAdapter.ViewHolder?, position: Int) {
        val project = data[position]
        holder?.update(project)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View?, val onClickListener: (Project) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun update(project: Project) {
            val nameView = itemView.findViewById(R.id.volunteer_name) as TextView
            val descriptionView = itemView.findViewById(R.id.volunteer_description) as TextView
            val imageView = itemView.findViewById(R.id.volunteer_image) as ImageView

            nameView.text = String.format("%s %s", project.name,
                    project.description)
            nameView.tag = project
            descriptionView.tag = project
            imageView.tag = project
            itemView.tag = project

            nameView.setOnClickListener(this::onClick)
            descriptionView.setOnClickListener(this::onClick)
            imageView.setOnClickListener(this::onClick)
            itemView.setOnClickListener(this::onClick)

            if (!project.images.isEmpty()) {
                val imageMetadata = project.images.first()
                if (imageMetadata.url.isNotEmpty()) {
                    Picasso.with(itemView.context)
                            .load(imageMetadata.url)
                            .into(imageView)
                }
            }
        }

        fun onClick(view: View) {
            onClickListener(view.tag as Project)
        }
    }
}