package com.github.luecy1.holodex.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.luecy1.holodex.databinding.ItemFanArtBinding

class FanArtAdapter : ListAdapter<FanArtItem, FanArtAdapter.ViewHolder>(ItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFanArtBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fanArtItem = getItem(position)
        holder.binding.fanArtItem = fanArtItem
        holder.binding.executePendingBindings()
    }

    inner class ViewHolder(val binding: ItemFanArtBinding) : RecyclerView.ViewHolder(binding.root)
}

private class ItemCallback : DiffUtil.ItemCallback<FanArtItem>() {
    override fun areItemsTheSame(oldItem: FanArtItem, newItem: FanArtItem): Boolean {
        return oldItem.tweetId == newItem.tweetId
    }

    override fun areContentsTheSame(oldItem: FanArtItem, newItem: FanArtItem): Boolean {
        return oldItem == newItem
    }
}
