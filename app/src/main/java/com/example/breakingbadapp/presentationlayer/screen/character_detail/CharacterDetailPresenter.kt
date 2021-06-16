package com.example.breakingbadapp.presentationlayer.screen.character_detail

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharacterDetailPresenter : MvpPresenter<CharacterDetailView>() {

    @Inject
    lateinit var repository: CharacterResponseRepository

    init {
        App.appComponent.inject(this)
    }

    fun getCharacterResponse(id: Int) {
        repository.getById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ character ->
                viewState.displayCharacter(character)
            }, { throwable: Throwable -> Timber.e(throwable)})
    }
}