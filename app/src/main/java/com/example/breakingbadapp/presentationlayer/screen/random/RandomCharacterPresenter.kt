package com.example.breakingbadapp.presentationlayer.screen.random

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.domainlayer.network.breakingbad.BreakingBadApi
import com.github.terrakok.cicerone.Router
import javax.inject.Inject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import timber.log.Timber

@InjectViewState
class RandomCharacterPresenter : MvpPresenter<RandomCharacterView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var api: BreakingBadApi

    override fun onFirstViewAttach() {
        App.appComponent.inject(   this)
        super.onFirstViewAttach()
    }

    fun onRandomCharacterTapped() {
        Single.create<Response<List<SerialCharacter>>> { subscriber ->
            subscriber.onSuccess(api.getRandomCharacter().execute())
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        viewState.displayRandomCharacter(it.first())
                        viewState.showCharacter()
                    }
                } else {
                    Timber.e("Random character response error")
                    viewState.hideCharacter()
                    viewState.displayMessage("Произошла ошибка запроса!")
                }
            }, {
                Timber.e("Response for random character failed: ${it.message}")
            })
    }
}