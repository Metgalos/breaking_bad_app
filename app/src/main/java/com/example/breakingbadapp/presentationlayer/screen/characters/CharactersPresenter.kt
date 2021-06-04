package com.example.breakingbadapp.presentationlayer.screen.characters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.repository.CharacterRepository
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharactersPresenter : MvpPresenter<CharactersFragmentView>() {

    @Inject
    lateinit var repository: CharacterRepository

    private var page = INITIAL_PAGE

    init {
        App.appComponent.inject(this)
        getCharacters()
    }

    fun getCharacters() {
        repository.getCharacters(page, PAGE_SIZE)
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        viewState.hideProgressBar()
                        viewState.addCharacters(it)
                        page ++
                    }
                } else {
                    Timber.i("Character response is failed")
                }
            }, { t: Throwable -> Timber.e(t) })
    }

    companion object {
        private const val INITIAL_PAGE = 1
        private const val PAGE_SIZE = 5
    }
}