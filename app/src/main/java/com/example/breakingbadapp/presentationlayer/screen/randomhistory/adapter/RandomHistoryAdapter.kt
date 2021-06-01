package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import android.view.ViewGroup
import com.example.breakingbadapp.presentationlayer.base.AutoloadingAdapter
import com.example.breakingbadapp.datalayer.entity.CharacterResponse


class RandomHistoryAdapter : AutoloadingAdapter<CharacterResponse, RandomHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomHistoryViewHolder {
        return RandomHistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RandomHistoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}