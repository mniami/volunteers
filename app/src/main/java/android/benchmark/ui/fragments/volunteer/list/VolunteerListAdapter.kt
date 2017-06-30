package android.benchmark.ui.fragments.volunteer.list

import android.benchmark.R
import android.benchmark.domain.Volunteer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class VolunteerListAdapter(val data: List<Volunteer>, val onClickListener: (Volunteer) -> Unit) :
        RecyclerView.Adapter<VolunteerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.volunteer_item, parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val volunteer = data[position]
        holder?.update(volunteer)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder(itemView: View?, val onClickListener: (Volunteer) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun update(volunteer: Volunteer) {
            val nameView = itemView.findViewById(R.id.name) as TextView
            val descriptionView = itemView.findViewById(R.id.description) as TextView
            val imageView = itemView.findViewById(R.id.image) as ImageView

            nameView.text = String.format("%s %s", volunteer.name,
                    volunteer.surname)
            if (volunteer.addresses.size > 0) {
                val volunteerAddress = volunteer.addresses.first()
                descriptionView.text = String.format("%s %s",
                        volunteerAddress.city, volunteerAddress.street)
            }
            nameView.tag = volunteer
            descriptionView.tag = volunteer
            imageView.tag = volunteer
            itemView.tag = volunteer

            nameView.setOnClickListener(this::onClick)
            descriptionView.setOnClickListener(this::onClick)
            imageView.setOnClickListener(this::onClick)
            itemView.setOnClickListener(this::onClick)

            if (!volunteer.avatarImageUri.isEmpty()) {
                Picasso.with(itemView.context)
                        .load(volunteer.avatarImageUri)
                        .into(imageView)
            }
        }

        fun onClick(view: View) {
            onClickListener(view.tag as Volunteer)
        }
    }
}