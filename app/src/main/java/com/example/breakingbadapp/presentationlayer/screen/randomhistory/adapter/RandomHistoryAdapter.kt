package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import android.view.ViewGroup
import com.example.breakingbadapp.presentationlayer.base.AutoloadingAdapter
import com.example.breakingbadapp.datalayer.entity.CharacterResponse


class RandomHistoryAdapter : AutoloadingAdapter<CharacterResponse, RandomHistoryViewHolder>() {

    private var listener: RandomHistoryViewHolderListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomHistoryViewHolder {
        return RandomHistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RandomHistoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, listener) }
    }

    fun setListener(listener: RandomHistoryViewHolderListener) {
        this.listener = listener
    }
}