package com.example.breakingbadapp.presentationlayer.screen.character_detail

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharacterDetailPresenter(
    private val responseId: Int
) : MvpPresenter<CharacterDetailView>() {

    @Inject
    lateinit var repository: CharacterResponseRepository

    init {
        App.appComponent.inject(this)
        getCharacterResponse()
    }

    private fun getCharacterResponse() {
        repository.getById(responseId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ character ->
                viewState.displayCharacter(character)
            }, { throwable: Throwable -> Timber.e(throwable)})
    }
}