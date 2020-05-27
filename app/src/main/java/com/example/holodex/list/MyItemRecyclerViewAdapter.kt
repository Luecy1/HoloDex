package com.example.holodex.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.holodex.R
import com.example.holodex.data.HoloLiverItem
import kotlinx.android.synthetic.main.fragment_item.view.*

class MyItemRecyclerViewAdapter :
    ListAdapter<HoloLiverItem, MyItemRecyclerViewAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.mContentView.text = item.name
        holder.mIdView.load(item.imageUrl)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val mIdView: ImageView = view.liver_image
        val mContentView: TextView = view.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<HoloLiverItem>() {
        override fun areItemsTheSame(oldItem: HoloLiverItem, newItem: HoloLiverItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HoloLiverItem, newItem: HoloLiverItem): Boolean {
            return oldItem == newItem
        }
    }
}
