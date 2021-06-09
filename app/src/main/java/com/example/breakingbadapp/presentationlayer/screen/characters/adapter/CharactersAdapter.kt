package com.example.breakingbadapp.presentationlayer.screen.characters.adapter

import android.view.ViewGroup
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.AutoloadingAdapter

class CharactersAdapter : AutoloadingAdapter<SerialCharacter, CharactersHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersHolder =
        CharactersHolder.create(parent)

    override fun onBindViewHolder(holder: CharactersHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}