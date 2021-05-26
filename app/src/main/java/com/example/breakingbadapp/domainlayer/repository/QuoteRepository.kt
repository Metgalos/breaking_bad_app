package com.example.breakingbadapp.domainlayer.repository

import com.example.breakingbadapp.datalayer.response.Quote
import com.example.breakingbadapp.domainlayer.network.breakingbad.BreakingBadApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: BreakingBadApi
) {

    fun getQuoteByAuthor(
        author: String,
        onSuccess: (Response<List<Quote>>) -> Unit,
        onError: (Throwable) -> Unit
    ): Disposable =

        Single.create<Response<List<Quote>>> {
            api.getQuoteByAuthor(author)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
}