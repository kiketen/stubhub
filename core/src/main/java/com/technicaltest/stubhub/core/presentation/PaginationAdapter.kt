package com.technicaltest.stubhub.core.presentation

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationAdapter<T : RecyclerView.ViewHolder, S>(
    items: MutableList<S>,
    private val onLoadPage: (Int) -> Unit
) : BaseAdapter<T, S>(items) {

    companion object {
        private const val NOT_VISIBLE_ITEMS_TRIGGER = 5
    }

    private var totalItems: Int = 0

    open val notVisibleItemsTrigger: Int = NOT_VISIBLE_ITEMS_TRIGGER

    private val triggerPosition
        get() = itemCount - notVisibleItemsTrigger

    override fun onBindViewHolder(holder: T, position: Int) {
        if (position > triggerPosition && !isLastPage()) {
            Handler().post { onLoadPage(itemCount) }
        }
    }

    fun setPaginationInfo(totalItems: Int) {
        this.totalItems = totalItems
    }

    private fun isLastPage() = itemCount >= totalItems
}