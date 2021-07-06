package com.example.breakingbadapp.presentationlayer.screen.search

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.network.breakingbad.repository.QuoteRepository
import com.example.breakingbadapp.extension.forQuery
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SearchQuotePresenter : MvpPresenter<SearchQuoteView>() {

    @Inject
    lateinit var repository: QuoteRepository

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appComponent.inject(this)
    }

    fun searchQuote(author: String) {
        repository.getQuoteByAuthor(
            author = author.forQuery(),
            onSuccess = { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (it.isEmpty()) {
                            viewState.displayNotFoundMessage()
                        } else {
                            viewState.displayQuotesList(it)
                        }
                    }
                } else {
                    Timber.e("Quote by author response is failed")
                    viewState.displayResponseError("Произошла непредвиденная ошибка")
                }
            },
            onError = {
                Timber.e(it)
                viewState.displayResponseError("Произошла непредвиденная ошибка")
            }
        )
        viewState.hideKeyboard()
    }
}