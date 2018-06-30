package guideme.volunteers.ui.fragments.volunteer.details.project

import guideme.volunteers.R
import guideme.volunteers.domain.ImageMetadata
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProjectImageAdapter(val context: Context, val layoutInflater: LayoutInflater, val onClickListener: (View, ImageMetadata) -> Unit) {
    fun instantiateItem(container: ViewGroup, image: ImageMetadata): Any {
        val itemView = layoutInflater.inflate(R.layout.project_image_item, container, false)

        itemView?.let {
            var imageView = it.findViewById<ImageView>(R.id.image_view)
            var titleView = it.findViewById<TextView>(R.id.title_view)

            imageView?.let {
                it.tag = image
                it.setOnClickListener { onClickListener.invoke(it, it.tag as ImageMetadata) }
                Picasso.with(it.context).load(image.url).into(it)
            }
            titleView?.let {
                it.tag = image
                it.setOnClickListener { onClickListener.invoke(it, it.tag as ImageMetadata) }
                it.text = image.name
            }
        }

        container.addView(itemView)

        return itemView
    }
}