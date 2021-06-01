package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.CharacterResponseRowBinding
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import javax.inject.Inject

class RandomHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = CharacterResponseRowBinding.bind(itemView)

    @Inject
    lateinit var imageLoader: ImageLoader

    init {
        App.appComponent.inject(this)
    }

    fun bind(character: CharacterResponse) {
        with (binding) {
            characterName.text = character.name
            characterStatus.text = character.status
            characterNickname.text = character.nickname
            characterActor.text = character.actor
            responseDatetime.text = character.datetime

            character.picture_url?.let {
                imageLoader.load(LoadPhotoConfig(url = it), characterPicture)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): RandomHistoryViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.character_response_row, parent, false)

            return RandomHistoryViewHolder(view)
        }
    }
}