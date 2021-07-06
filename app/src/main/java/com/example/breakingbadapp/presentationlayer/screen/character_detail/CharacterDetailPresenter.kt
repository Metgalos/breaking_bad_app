package com.example.breakingbadapp.presentationlayer.screen.character_detail

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.core.ResourceProvider
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.RandomHistoryFragment
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.RandomHistoryPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class CharacterDetailPresenter(
    private val responseId: Int
) : MvpPresenter<CharacterDetailView>() {

    @Inject
    lateinit var repository: CharacterResponseRepository

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private var character: CharacterResponse? = null

    init {
        App.appComponent.inject(this)
        getCharacterResponse()
    }

    fun removeCharacterResponse() {
        character?.let { response ->
            repository.remove(response)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val message = resourceProvider.getString(R.string.delete_item_success)
                    viewState.displayMessage(message)
                    router.backTo(RandomHistoryFragment.getScreen())
                },
                    { t: Throwable -> Timber.e(t) }
                )
        }
    }

    private fun getCharacterResponse() {
        repository.getById(responseId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ character ->
                this.character = character
                viewState.displayCharacter(character)
            }, { throwable: Throwable -> Timber.e(throwable) })
    }
}