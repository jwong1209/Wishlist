package com.example.wishlist

import WishlistItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(private val wishlist: List<WishlistItem>) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.
        val itemTv : TextView
        val priceTv: TextView
        val urlTv: TextView


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            itemTv = itemView.findViewById(R.id.itemTv)
            priceTv = itemView.findViewById(R.id.priceTv)
            urlTv = itemView.findViewById(R.id.urlTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Populate data into the item through the holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get the data model based on position
        val wishlistItem = wishlist.get(position)
        // Set item views based on views and data model
        holder.itemTv.text = wishlistItem.itemName
        holder.priceTv.text = wishlistItem.number.toString()
        holder.urlTv.text = wishlistItem.url
        holder.itemView.setOnLongClickListener{
            (wishlist as MutableList).removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, wishlist.size)
            true
        }
    }

    override fun getItemCount(): Int {
        return wishlist.size
    }
}