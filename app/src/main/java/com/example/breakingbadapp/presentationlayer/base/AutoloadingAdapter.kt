package com.example.breakingbadapp.presentationlayer.base

import androidx.recyclerview.widget.RecyclerView


abstract class AutoloadingAdapter<R, T : RecyclerView.ViewHolder> : RecyclerView.Adapter<T>() {

    private var data: ArrayList<R> = arrayListOf()

    override fun getItemCount(): Int = data.size

    fun getItem(position: Int): R? {
        return if (position <= getLastPosition()) data[position] else null
    }

    fun getLastPosition(): Int = itemCount - 1

    fun addItems(data: List<R>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun deleteItem(item: R) {
        data.remove(item)
        notifyDataSetChanged()
    }
}