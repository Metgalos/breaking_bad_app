package com.example.breakingbadapp.presentationlayer.screen.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.FragmentCharacterBinding
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import javax.inject.Inject

class CharacterFragment(
    private val character: SerialCharacter
) : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var binding: FragmentCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        setCharacterData()
        return binding.root
    }

    private fun setCharacterData() {
        with(binding) {
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
}