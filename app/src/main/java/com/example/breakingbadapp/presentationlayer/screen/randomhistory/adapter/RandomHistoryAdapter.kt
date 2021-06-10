package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.breakingbadapp.datalayer.entity.CharacterResponse as Character


class RandomHistoryAdapter : ListAdapter<Character, RandomHistoryViewHolder>(Callback()) {

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

    class Callback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(old: Character, new: Character): Boolean {
            return old.id == new.id
        }

        override fun areContentsTheSame(old: Character, new: Character): Boolean {
            return (old == new)
        }
    }
}