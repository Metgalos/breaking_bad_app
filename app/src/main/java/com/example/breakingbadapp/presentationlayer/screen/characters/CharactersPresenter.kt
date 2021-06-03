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

    init {
        App.appComponent.inject(this)
    }

    fun getCharacters() {
        repository.getCharacters()
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        viewState.hideProgressBar()
                        viewState.displayCharacters(it)
                    }
                } else {
                    Timber.i("Character response is failed")
                }
            }, { t: Throwable -> Timber.e(t) })
    }
}