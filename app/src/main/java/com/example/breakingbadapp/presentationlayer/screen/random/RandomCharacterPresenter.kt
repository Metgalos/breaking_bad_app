package com.example.breakingbadapp.presentationlayer.screen.random

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.repository.CharacterRepository
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

@InjectViewState
class RandomCharacterPresenter : MvpPresenter<RandomCharacterView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var characterRepository: CharacterRepository

    override fun onFirstViewAttach() {
        App.appComponent.inject(   this)
        super.onFirstViewAttach()
    }

    fun onRandomCharacterTapped() {
        characterRepository.getRandomCharacter(
            onSuccess = { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        viewState.setRandomCharacterData(it.first())
                        viewState.showCharacter()
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