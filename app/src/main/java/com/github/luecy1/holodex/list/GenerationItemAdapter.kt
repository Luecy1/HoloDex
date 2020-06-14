package com.github.luecy1.holodex.list

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.databinding.ItemGenerationBinding


class GenerationItemAdapter(
    private val context: Context,
    private val viewModel: HololiveListViewModel
) :
    ListAdapter<GenerationItem, GenerationItemAdapter.ViewHolder>(ItemCallback()) {

    private val layoutInflater = LayoutInflater.from(context)

    private val adapterCache = mutableMapOf<String, HololiveMemberItemAdapter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenerationBinding.inflate(layoutInflater, parent, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }

        binding.recyclerView.addItemDecoration(CustomItemDecoration.createDefaultDecoration(context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val generation = getItem(position)
        holder.generationText.text = generation.name

        val adapter = adapterCache.getOrPut(generation.name) {
            HololiveMemberItemAdapter(context, viewModel).apply {
                submitList(generation.hololiverList)
            }
        }

        holder.recyclerView.adapter = adapter
    }

    inner class ViewHolder(binding: ItemGenerationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val generationText = binding.generationText
        val recyclerView = binding.recyclerView
    }

    class ItemCallback : DiffUtil.ItemCallback<GenerationItem>() {
        override fun areItemsTheSame(oldItem: GenerationItem, newItem: GenerationItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GenerationItem, newItem: GenerationItem): Boolean {
            return oldItem == newItem
        }
    }
}

class CustomItemDecoration(private val space: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space
        outRect.left = space
        outRect.right = space
        outRect.bottom = space
    }

    companion object {
        fun createDefaultDecoration(context: Context): CustomItemDecoration {
            val spacingInPixels =
                context.resources.getDimensionPixelSize(R.dimen.hololiveItemMargin)
            return CustomItemDecoration(spacingInPixels)
        }
    }
}
