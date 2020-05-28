package com.example.holodex.list

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class GridDecoration(private val sideMargin: Int) : ItemDecoration() {

    private val halfMargin: Int = sideMargin / 2
    private val halfMinusMargin: Int = -1 * halfMargin
    private var initializedRecycler = false

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (parent.layoutManager !is GridLayoutManager) {
            super.getItemOffsets(outRect, view, parent, state)
            return
        }

        setRecyclerAttr(parent)

        val adapterPosition: Int = parent.getChildAdapterPosition(view)
        if (RecyclerView.NO_POSITION == adapterPosition) return

        val lm = parent.layoutManager as GridLayoutManager
        val ssl = lm.spanSizeLookup

        val spanCount = lm.spanCount

        if (ssl.getSpanSize(adapterPosition) >= spanCount) {
            // 全幅以上の場合は余白をとらないようにマイナスマージンをつける
            val params = view.layoutParams as MarginLayoutParams
            params.setMargins(halfMinusMargin, params.topMargin, halfMinusMargin, sideMargin)
            view.layoutParams = params
            super.getItemOffsets(outRect, view, parent, state)
            return
        }

        outRect.left = halfMargin
        outRect.right = halfMargin
        outRect.bottom = sideMargin
    }

    private fun setRecyclerAttr(parent: RecyclerView) {
        if (initializedRecycler) return
        initializedRecycler = true
        parent.clipToPadding = false
        parent.setPadding(
            halfMargin
            , parent.paddingTop
            , halfMargin
            , parent.paddingBottom
        )
    }
}