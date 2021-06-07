package com.example.breakingbadapp.presentationlayer.screen.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.databinding.FragmentRandomCharacterBinding
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class RandomCharacterFragment : BaseFragment(), RandomCharacterView {

    @InjectPresenter
    lateinit var presenter: RandomCharacterPresenter

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var binding: FragmentRandomCharacterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)
        binding = FragmentRandomCharacterBinding.inflate(inflater, container, false)

        binding.randomCharacterButton.setOnClickListener {
            presenter.getRandomCharacter()
        }
        return binding.root
    }

    override fun hideCharacter() {
        binding.randomCharacterLayout.isVisible = false
    }

    override fun showCharacter(serialCharacter: SerialCharacter) {
        with(binding) {
            randomCharacterName.text = serialCharacter.name ?: EMPTY_FIELD_TEXT
            randomCharacterBirthday.text = serialCharacter.birthday ?: EMPTY_FIELD_TEXT
            randomCharacterStatus.text = serialCharacter.status ?: EMPTY_FIELD_TEXT
            randomCharacterNickname.text = serialCharacter.nickname ?: EMPTY_FIELD_TEXT
            randomCharacterActor.text = serialCharacter.actor ?: EMPTY_FIELD_TEXT

            serialCharacter.picture?.let { url ->
                imageLoader.load(LoadPhotoConfig(url), randomCharacterImage)
            }
        }

        binding.randomCharacterPlaceholder.isVisible = false
        binding.randomCharacterLayout.isVisible = true
    }

    companion object {
        private const val EMPTY_FIELD_TEXT = "-"

        fun getScreen() = FragmentScreen {
            RandomCharacterFragment()
        }
    }
}