package com.example.breakingbadapp.presentationlayer.screen.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.FragmentCharacterDetailBinding
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class CharacterDetailFragment : BaseFragment(), CharacterDetailView {

    @InjectPresenter
    lateinit var presenter: CharacterDetailPresenter

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var binding: FragmentCharacterDetailBinding

    override fun getToolbarContainer(): Int = R.id.toolbar_container

    override fun getToolbarId(): Int = R.id.toolbar

    override fun getToolbarLayout(): Int = R.layout.base_toolbar

    @ProvidePresenter
    fun provideCharacterDetailPresenter(): CharacterDetailPresenter? {
        return arguments?.getInt(CHARACTER_RESPONSE_ID_ARG_KEY)?.let {
            CharacterDetailPresenter(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.appComponent.inject(this)
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        initToolbarBackButton { presenter.onBackButtonClick() }
    }

    override fun displayCharacter(character: CharacterResponse) {
        setTitle(character.name)
        with(binding) {
            randomCharacterName.text = character.name ?: EMPTY_FIELD_TEXT
            randomCharacterBirthday.text = character.birthday ?: EMPTY_FIELD_TEXT
            randomCharacterStatus.text = character.status ?: EMPTY_FIELD_TEXT
            randomCharacterNickname.text = character.nickname ?: EMPTY_FIELD_TEXT
            randomCharacterActor.text = character.actor ?: EMPTY_FIELD_TEXT

            character.picture_url?.let { url ->
                imageLoader.load(LoadPhotoConfig(url), randomCharacterImage)
            }
        }
    }

    companion object {
        private const val CHARACTER_RESPONSE_ID_ARG_KEY = "characterId"
        private const val EMPTY_FIELD_TEXT = "-"

        fun getScreen(characterResponseId: Int) = FragmentScreen {
            CharacterDetailFragment().apply {
                arguments = Bundle().apply {
                    putInt(CHARACTER_RESPONSE_ID_ARG_KEY, characterResponseId)
                }
            }
        }
    }
}