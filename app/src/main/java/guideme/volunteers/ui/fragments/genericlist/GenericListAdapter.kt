package guideme.volunteers.ui.fragments.genericlist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import guideme.volunteers.R
import guideme.volunteers.ui.utils.CircleTransform

class GenericListAdapter(val list: List<GenericItem<*>>, val onClickListener: (GenericItem<*>?) -> Unit) : RecyclerView.Adapter<GenericListAdapter.ViewHolder>() {
    var filteredList = list

    fun filter(text: String) {
        if (text.isBlank()) {
            filteredList = list
        } else {
            filteredList = list.filter { item ->
                item.title.contains(text, true) || item.subTitle.contains(text, true)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericListAdapter.ViewHolder {
        return GenericListAdapter.ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.generic_item, parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: GenericListAdapter.ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.update(item)
    }

    override fun getItemCount(): Int = filteredList.size

    class ViewHolder(itemView: View?, val onClickListener: (GenericItem<*>?) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun update(item: GenericItem<*>?) {
            val titleView = itemView.findViewById<TextView>(R.id.title)
            val subtitleView = itemView.findViewById<TextView>(R.id.subtitle)
            val imageView = itemView.findViewById<ImageView>(R.id.image)

            titleView.text = item?.title
            subtitleView.text = item?.subTitle

            titleView.tag = item
            subtitleView.tag = item
            imageView.tag = item
            itemView.tag = item

            titleView.setOnClickListener(this::onClick)
            subtitleView.setOnClickListener(this::onClick)
            imageView.setOnClickListener(this::onClick)
            itemView.setOnClickListener(this::onClick)

            if (item?.imageUrl != null && item.imageUrl.isNotEmpty()) {
                Picasso.with(itemView.context).load(item.imageUrl).placeholder(R.mipmap.human_placeholder).transform(CircleTransform()).into(imageView)
            } else {

            }

        }

        fun onClick(view: View) {
            onClickListener(view.tag as GenericItem<*>?)
        }
    }
}