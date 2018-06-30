package guideme.volunteers.ui.fragments.volunteer.details.project

import guideme.volunteers.R
import guideme.volunteers.domain.Project
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProjectListAdapter(val data: List<Project>, val onClickListener: (Project) -> Unit) :
        RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.project_item_view, parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = data[position]
        holder?.update(project)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View?, val onClickListener: (Project) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun update(project: Project) {
            val nameView = itemView.findViewById<TextView>(R.id.name)
            val descriptionView = itemView.findViewById<TextView>(R.id.description)
            val imageView = itemView.findViewById<ImageView>(R.id.image)

            nameView.tag = project
            descriptionView.tag = project
            imageView.tag = project
            itemView.tag = project

            nameView.text = project.name
            descriptionView.text = project.description

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