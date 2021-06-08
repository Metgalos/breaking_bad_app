package com.example.breakingbadapp.presentationlayer.screen.random

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import com.example.breakingbadapp.domainlayer.repository.CharacterRepository
import com.example.breakingbadapp.extension.toCharacterResponse
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.schedulers.Schedulers
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

    fun getRandomCharacter() {
        characterRepository.getRandomCharacter(
            onComplete = { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        val character = it.first()
                        viewState.showCharacter(character)
                        dbRepository.insert(character.toCharacterResponse())
                            .subscribeOn(Schedulers.computation())
                            .subscribe(
                                {},
                                { throwable: Throwable? -> Timber.e(throwable) }
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