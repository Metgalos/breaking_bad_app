package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import android.view.ViewGroup
import com.example.breakingbadapp.presentationlayer.base.AutoloadingAdapter
import com.example.breakingbadapp.datalayer.entity.CharacterResponse


class RandomHistoryAdapter : AutoloadingAdapter<CharacterResponse, RandomHistoryViewHolder>() {

    private var listener: RandomHistoryViewHolderListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomHistoryViewHolder {
        return RandomHistoryViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: RandomHistoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

    override fun getItemViewType(position: Int): Int {
        val type = getViewHolderType(position)
        return RandomHistoryHolderType.getIntValue(type)
    }

    private fun getViewHolderType(position: Int): RandomHistoryHolderType =
        when (position) {
            FIRST_ITEM_POSITION -> RandomHistoryHolderType.FIRST
            itemCount.minus(1) -> RandomHistoryHolderType.LAST
            else -> RandomHistoryHolderType.MIDDLE
        }

    fun setListener(listener: RandomHistoryViewHolderListener) {
        this.listener = listener
    }

    companion object {
        private const val FIRST_ITEM_POSITION = 0
    }
}