package com.example.breakingbadapp.presentationlayer.screen.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.FragmentCharactersBinding
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.screen.characters.adapter.CharactersAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class CharactersFragment : BaseFragment<FragmentCharactersBinding>(), CharactersFragmentView {

    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    private val viewAdapter by lazy {
        CharactersAdapter()
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCharactersBinding
        get() = FragmentCharactersBinding::inflate

    override fun getToolbarContainer(): Int = R.id.toolbar_container

    override fun getToolbarId(): Int = R.id.toolbar

    override fun getToolbarLayout(): Int = R.layout.base_toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        setTitle(R.string.characters_toolbar_title)

        with(binding.charactersList) {
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addOnScrollListener(presenter.getOnScrollListener())
        }
    }

    override fun addCharacters(characters: List<SerialCharacter>) {
        viewAdapter.addItems(characters)
    }

    override fun hideProgressBar() {
        binding.charactersProgressBar.isVisible = false
    }

    companion object {
        fun getScreen() = FragmentScreen { CharactersFragment() }
    }
}