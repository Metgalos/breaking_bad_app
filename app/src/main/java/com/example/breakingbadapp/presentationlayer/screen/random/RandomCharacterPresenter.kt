package com.example.breakingbadapp.presentationlayer.screen.random

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import com.example.breakingbadapp.domainlayer.repository.CharacterRepository
import com.example.breakingbadapp.extension.toCharacterResponse
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import timber.log.Timber

@InjectViewState
class RandomCharacterPresenter : MvpPresenter<RandomCharacterView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var characterRepository: CharacterRepository

    @Inject
    lateinit var dbRepository: CharacterResponseRepository

    override fun onFirstViewAttach() {
        App.appComponent.inject(   this)
        super.onFirstViewAttach()
    }

    fun onRandomCharacterTapped() {
        characterRepository.getRandomCharacter(
            onComplete = { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        val character = it.first()
                        viewState.setRandomCharacterData(character)
                        viewState.showCharacter()
                        dbRepository.insert(
                            character.toCharacterResponse(),
                            {},
                            { throwable ->  Timber.e(throwable) }
                        )
                    }
                } else {
                    Timber.e("Random character response error")
                    viewState.hideCharacter()
                    viewState.displayMessage("Произошла ошибка запроса!")
                }
            },
            onError = { throwable -> Timber.i(throwable) }
        )
    }
}