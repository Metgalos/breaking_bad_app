package com.example.breakingbadapp.presentationlayer.screen.characters.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.breakingbadapp.datalayer.response.SerialCharacter as Character

class CharactersAdapter : ListAdapter<Character, CharactersHolder>(Callback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersHolder =
        CharactersHolder.create(parent)

    override fun onBindViewHolder(holder: CharactersHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    fun addItems(characters: List<Character>) {
        val list = currentList.toMutableList().apply { addAll(characters) }
        submitList(list)
    }

    fun getLastPosition() = itemCount.minus(1)

    class Callback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
}