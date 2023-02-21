package com.technicaltest.stubhub.core.presentation

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : RecyclerView.ViewHolder, S>(
    private val items: MutableList<S>
) : RecyclerView.Adapter<T>() {

    override fun getItemCount() = items.size

    fun update(itemList: List<S>){
        items.clear()
        items.addAll(itemList)
        notifyDataSetChanged()
    }

    fun deleteLast() {
        items.removeLast()
        notifyDataSetChanged()
    }

}