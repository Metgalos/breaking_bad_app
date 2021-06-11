package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.breakingbadapp.extension.fromDbFormatToHeader
import com.example.breakingbadapp.datalayer.entity.CharacterResponse as Character

class RandomHistoryAdapter : ListAdapter<RandomHistoryViewHolder.Model, RandomHistoryViewHolder>(Callback()) {

    private var listener: RandomHistoryViewHolderListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomHistoryViewHolder {
        return RandomHistoryViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: RandomHistoryViewHolder, position: Int) {
        when (holder) {
            is RandomHistoryViewHolder.HeaderItemHolder -> {
                val item = getItem(position) as RandomHistoryViewHolder.Model.Header
                holder.bind(item.header)
            }
            is RandomHistoryViewHolder.ItemHolder -> {
                val item = getItem(position) as RandomHistoryViewHolder.Model.Item
                holder.bind(item.response, listener)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val type = getViewHolderType(position)
        return RandomHistoryHolderType.getIntValue(type)
    }

    fun setData(list: List<Character>?) {
        val data = mutableListOf<RandomHistoryViewHolder.Model>()
        list?.let {
            var prev = it.first().datetime?.fromDbFormatToHeader()
            prev?.let { date -> data.add(RandomHistoryViewHolder.Model.Header(date)) }

            it.forEach { character ->
                character.datetime?.let { datetime ->
                    val date = datetime.fromDbFormatToHeader()
                    if (prev != date) {
                        data.add(RandomHistoryViewHolder.Model.Header(date))
                        prev = date
                    }
                }
                data.add(RandomHistoryViewHolder.Model.Item(character))
            }
        }
        super.submitList(data)
    }

    fun setListener(listener: RandomHistoryViewHolderListener) {
        this.listener = listener
    }

    private fun getViewHolderType(position: Int): RandomHistoryHolderType {
        val item = getItem(position)
        if (item is RandomHistoryViewHolder.Model.Header) {
            return RandomHistoryHolderType.HEADER
        }
        return when {
            !equalsWithPrevious(position) -> RandomHistoryHolderType.FIRST
            !equalsWithNext(position) -> RandomHistoryHolderType.LAST
            else -> RandomHistoryHolderType.MIDDLE
        }
    }

    private fun equalsWithNext(position: Int): Boolean {
        return position + 1 < itemCount && getItem(position + 1) is RandomHistoryViewHolder.Model.Item
    }

    private fun equalsWithPrevious(position: Int): Boolean {
        return position - 1 > 0 && getItem(position - 1) is RandomHistoryViewHolder.Model.Item
    }

    class Callback : DiffUtil.ItemCallback<RandomHistoryViewHolder.Model>() {
        override fun areItemsTheSame(
            oldItem: RandomHistoryViewHolder.Model,
            newItem: RandomHistoryViewHolder.Model
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RandomHistoryViewHolder.Model,
            newItem: RandomHistoryViewHolder.Model
        ): Boolean {
            return oldItem == newItem
        }
    }
}