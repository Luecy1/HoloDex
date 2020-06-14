package com.github.luecy1.holodex.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.databinding.ItemHololiveBinding

class HololiveMemberItemAdapter(
    context: Context,
    private val viewModel: HololiveListViewModel
) :
    ListAdapter<HololiverItem, HololiveMemberItemAdapter.ViewHolder>(ItemCallback()) {

    private val inflater = LayoutInflater.from(context)

    private val round = context.resources.getDimension(R.dimen.hololiveItemRound)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHololiveBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item, viewModel)
    }

    inner class ViewHolder(val binding: ItemHololiveBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: HololiverItem, viewModel: HololiveListViewModel) {
            binding.item = item
            binding.liverImage.load(item.imageUrl) {
                transformations(RoundedCornersTransformation(topLeft = round, topRight = round))
            }
            binding.viewModel = viewModel
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<HololiverItem>() {
        override fun areItemsTheSame(oldItem: HololiverItem, newItem: HololiverItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HololiverItem, newItem: HololiverItem): Boolean {
            return oldItem == newItem
        }
    }
}
