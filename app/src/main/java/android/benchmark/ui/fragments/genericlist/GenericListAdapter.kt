package android.benchmark.ui.fragments.genericlist

import android.benchmark.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class GenericListAdapter(val genericListProvider: GenericListProvider, val onClickListener: (GenericItem) -> Unit) : RecyclerView.Adapter<GenericListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GenericListAdapter.ViewHolder {
        return GenericListAdapter.ViewHolder(LayoutInflater.from(parent?.context)
                .inflate(R.layout.generic_item, parent, false), onClickListener)
    }

    override fun onBindViewHolder(holder: GenericListAdapter.ViewHolder?, position: Int) {
        val data = genericListProvider.list
        val item = data[position]
        holder?.update(item)
    }

    override fun getItemCount(): Int {
        return genericListProvider.list.size
    }

    class ViewHolder(itemView: View?, val onClickListener: (GenericItem) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun update(item : GenericItem) {
            val titleView = itemView.findViewById(R.id.title) as TextView
            val subtitleView = itemView.findViewById(R.id.subtitle) as TextView
            val imageView = itemView.findViewById(R.id.image) as ImageView

            titleView.text = item.title

            titleView.tag = item
            subtitleView.tag = item
            imageView.tag = item
            itemView.tag = item

            titleView.setOnClickListener(this::onClick)
            subtitleView.setOnClickListener(this::onClick)
            imageView.setOnClickListener(this::onClick)
            itemView.setOnClickListener(this::onClick)

            if (!item.imageUrl.isEmpty()) {
                Picasso.with(itemView.context)
                        .load(item.imageUrl)
                        .into(imageView)
            }
        }

        fun onClick(view: View) {
            onClickListener(view.tag as GenericItem)
        }
    }
}