package com.technicaltest.stubhub.core.presentation

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder, S>(
    private val items: MutableList<S>
) : RecyclerView.Adapter<T>() {

    override fun getItemCount() = items.size

    fun add(item: S) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun add(itemList: List<S>) {
        items.addAll(itemList)
        notifyDataSetChanged()
    }

    fun deleteLast() {
        items.removeLast()
        notifyDataSetChanged()
    }
}