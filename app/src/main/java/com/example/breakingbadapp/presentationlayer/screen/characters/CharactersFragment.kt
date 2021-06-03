package com.example.breakingbadapp.presentationlayer.screen.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.databinding.FragmentCharactersBinding
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.screen.character.CharacterFragment
import com.example.breakingbadapp.presentationlayer.screen.characters.adapter.CharactersScreenSlideAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class CharactersFragment : BaseFragment(), CharactersFragmentView {

    private lateinit var binding: FragmentCharactersBinding

    private lateinit var pager: ViewPager2

    @InjectPresenter
    lateinit var presenter: CharactersPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        pager = binding.charactersPager
        presenter.getCharacters()
        return binding.root
    }

    override fun displayCharacters(characters: List<SerialCharacter>) {
        characters.map { CharacterFragment(it) }.let { list ->
            pager.adapter = CharactersScreenSlideAdapter(this, list)
        }
    }

    override fun hideProgressBar() {
        binding.charactersProgressBar.isVisible = false
    }

    companion object {
        fun getScreen() = FragmentScreen { CharactersFragment() }
    }
}