package com.example.breakingbadapp.presentationlayer.screen.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.databinding.FragmentCharactersBinding
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.screen.character.CharacterFragment
import com.example.breakingbadapp.presentationlayer.screen.characters.adapter.CharactersScreenSlideAdapter
import com.example.breakingbadapp.presentationlayer.screen.characters.adapter.CharactersScreenSlideAdapterListener
import com.github.terrakok.cicerone.androidx.FragmentScreen

class CharactersFragment : BaseFragment(), CharactersFragmentView {

    private lateinit var binding: FragmentCharactersBinding

    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    private val adapter: CharactersScreenSlideAdapter by lazy {
        val listener = object : CharactersScreenSlideAdapterListener {
            override fun onLoading() {
                presenter.getCharacters()
            }
        }
        CharactersScreenSlideAdapter(this, listener)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        binding.charactersPager.adapter = adapter
        return binding.root
    }

    override fun addCharacters(characters: List<SerialCharacter>) {
        characters.map { CharacterFragment(it) }.let {
            adapter.addItems(it)
            if (it.isEmpty()) adapter.onLoadingEnd()
        }
    }

    override fun hideProgressBar() {
        binding.charactersProgressBar.isVisible = false
    }

    companion object {
        fun getScreen() = FragmentScreen { CharactersFragment() }
    }
}