package com.example.holodex.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.holodex.databinding.StreamInfoItemBinding


class StreamInfoAdapter(context: Context) :
    ListAdapter<StreamItem, StreamInfoAdapter.ViewHolder>(ItemCallback()) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StreamInfoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val streamInfo = getItem(position)

        holder.binding.item = streamInfo
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(
        val binding: StreamInfoItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class ItemCallback : DiffUtil.ItemCallback<StreamItem>() {
        override fun areItemsTheSame(oldItem: StreamItem, newItem: StreamItem): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: StreamItem, newItem: StreamItem): Boolean {
            return oldItem == newItem
        }
    }

}