package com.example.breakingbadapp.presentationlayer.screen.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.presentationlayer.screen.characters.CharactersFragment
import com.example.breakingbadapp.presentationlayer.screen.random.RandomCharacterFragment
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.RandomHistoryFragment
import com.example.breakingbadapp.presentationlayer.screen.search.SearchQuoteFragment
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appComponent.inject(this)
        loadInitialScreen()
    }

    fun onBottomNavigationItemSelected(itemId: Int): Boolean {
        when (itemId) {
            R.id.random_character -> {
                navigateToRandomCharacterFragment()
                return true
            }
            R.id.search_quote -> {
                navigateToSearchCharacterFragment()
                return true
            }
            R.id.random_character_history -> {
                navigateToRandomCharacterHistoryFragment()
                return true
            }
            R.id.characters -> {
                navigateToCharacters()
                return true
            }
            else -> return false
        }
    }

    private fun navigateToCharacters() {
        router.replaceScreen(CharactersFragment.getScreen())
    }

    private fun navigateToRandomCharacterHistoryFragment() {
        router.replaceScreen(RandomHistoryFragment.getScreen())
    }

    private fun loadInitialScreen() {
        router.replaceScreen(RandomCharacterFragment.getScreen())
    }

    private fun navigateToSearchCharacterFragment() {
        router.replaceScreen(SearchQuoteFragment.getScreen())
    }

    private fun navigateToRandomCharacterFragment() {
        loadInitialScreen()
    }
}