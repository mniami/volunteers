package android.benchmark.ui.fragments.volunteer

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ListAdapter(val data: List<Volunteer>) :
        RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.volunteer_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder?, position: Int) {
        val volunteer = data[position]
        holder?.update(volunteer)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun update(volunteer: Volunteer) {
            val nameView = itemView.findViewById(R.id.volunteer_name) as
                    TextView
            val descriptionView = itemView.findViewById(R.id
                    .volunteer_description) as TextView
            val imageView = itemView.findViewById(R.id.volunteer_image) as
                    ImageView

            nameView.text = String.format("%s %s", volunteer.name,
                    volunteer.surname)
            if (volunteer.addresses.size > 0) {
                val volunteerAddress = volunteer.addresses.first()
                descriptionView.text = String.format("%s %s",
                        volunteerAddress.city, volunteerAddress.street)
            }
            Picasso.with(itemView.context)
                    .load(volunteer.avatarImageUri)
                    .into(imageView)
        }
    }
}