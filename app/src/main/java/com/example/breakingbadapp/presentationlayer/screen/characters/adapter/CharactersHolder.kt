package com.example.breakingbadapp.presentationlayer.screen.characters.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.CharacterListItemBinding
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import javax.inject.Inject

class CharactersHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @Inject
    lateinit var imageLoader: ImageLoader

    private val binding = CharacterListItemBinding.bind(itemView)

    init {
        App.appComponent.inject(this)
    }

    fun bind(character: SerialCharacter) {
        with (binding) {
            scrollCharacterName.text = character.name
            scrollCharacterBirthday.text = character.birthday
            scrollCharacterStatus.text = character.status
            scrollCharacterNickname.text = character.nickname
            scrollCharacterActor.text = character.actor

            character.picture?.let {
                val config = LoadPhotoConfig(it, R.drawable.avatar_placeholder)
                imageLoader.load(config, scrollCharacterPicture)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): CharactersHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.character_list_item, parent, false)

            return CharactersHolder(view)
        }
    }
}