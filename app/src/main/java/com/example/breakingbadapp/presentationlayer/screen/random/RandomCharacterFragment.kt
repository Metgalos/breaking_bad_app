package com.example.breakingbadapp.presentationlayer.screen.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.appComponent.inject(this)

        binding.randomCharacterButton.setOnClickListener {
            presenter.onRandomCharacterTapped()
        }
    }

    override fun getRootViewLayoutId(): Int = R.layout.fragment_random_character

    override fun setRandomCharacterData(serialCharacter: SerialCharacter) {
        with (binding) {
            randomCharacterName.text = serialCharacter.name ?: EMPTY_FIELD_TEXT
            randomCharacterBirthday.text = serialCharacter.birthday ?: EMPTY_FIELD_TEXT
            randomCharacterStatus.text = serialCharacter.status ?: EMPTY_FIELD_TEXT
            randomCharacterNickname.text = serialCharacter.nickname ?: EMPTY_FIELD_TEXT
            randomCharacterActor.text = serialCharacter.actor ?: EMPTY_FIELD_TEXT

            serialCharacter.picture?.let { url ->
                imageLoader.load(LoadPhotoConfig(url), randomCharacterImage)
            }
        }
    }

    override fun hideCharacter() {
        binding.randomCharacterLayout.isVisible = false
    }

    override fun showCharacter() {
        binding.randomCharacterLayout.isVisible = true
    }

    companion object {
        private const val EMPTY_FIELD_TEXT = "-"

        fun getScreen() = FragmentScreen {
            RandomCharacterFragment()
        }
    }
}