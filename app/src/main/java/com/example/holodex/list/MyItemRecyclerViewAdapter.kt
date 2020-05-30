package com.example.holodex.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.holodex.R
import com.example.holodex.data.HoloLiverItem
import com.example.holodex.databinding.FragmentHololiveItemBinding

class MyItemRecyclerViewAdapter(context: Context) :
    ListAdapter<HoloLiverItem, MyItemRecyclerViewAdapter.ViewHolder>(ItemCallback()) {

    private val inflater = LayoutInflater.from(context)

    private val round = context.resources.getDimension(R.dimen.hololiveItemRound)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentHololiveItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.mContentView.text = item.name
        holder.mIdView.load(item.imageUrl) {
            transformations(RoundedCornersTransformation(topLeft = round, topRight = round))
        }
    }

    inner class ViewHolder(binding: FragmentHololiveItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val mIdView: ImageView = binding.liverImage
        val mContentView: TextView = binding.content
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
