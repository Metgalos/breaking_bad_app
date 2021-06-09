package com.example.breakingbadapp.presentationlayer.screen.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.databinding.FragmentCharactersBinding
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.screen.characters.adapter.CharactersAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen
import timber.log.Timber

class CharactersFragment : BaseFragment(), CharactersFragmentView {

    private lateinit var binding: FragmentCharactersBinding

    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    private val viewAdapter by lazy {
        CharactersAdapter()
    }

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)

        with (binding.charactersList) {
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addOnScrollListener(presenter.getOnScrollListener())
        }

        return binding.root
    }

    override fun addCharacters(characters: List<SerialCharacter>) {
        if (characters.isEmpty()) {

        }

        viewAdapter.addItems(characters)
    }

    override fun hideProgressBar() {
        binding.charactersProgressBar.isVisible = false
    }

    companion object {
        fun getScreen() = FragmentScreen { CharactersFragment() }
    }
}