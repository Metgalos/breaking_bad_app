package com.example.breakingbadapp.presentationlayer.screen.character_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.core.ResourceProvider
import com.example.breakingbadapp.databinding.FragmentCharacterDetailBinding
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

class CharacterDetailFragment : BaseFragment<FragmentCharacterDetailBinding>(), CharacterDetailView {

    @InjectPresenter
    lateinit var presenter: CharacterDetailPresenter

    @Inject
    lateinit var imageLoader: ImageLoader

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharacterDetailBinding
        get() = FragmentCharacterDetailBinding::inflate

    override fun getToolbarContainer(): Int = R.id.toolbar_container

    override fun getToolbarId(): Int = R.id.toolbar

    override fun getToolbarLayout(): Int = R.layout.base_toolbar

    @ProvidePresenter
    fun provideCharacterDetailPresenter(): CharacterDetailPresenter? {
        return arguments?.getInt(CHARACTER_RESPONSE_ID_ARG_KEY)?.let {
            CharacterDetailPresenter(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        initToolbar()
        initToolbarBackButton()
        initMenu(R.menu.response_detail_menu)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_item -> {
                presenter.removeCharacterResponse()
                return true
            }
            else -> super.onOptionsItemSelected(item)
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