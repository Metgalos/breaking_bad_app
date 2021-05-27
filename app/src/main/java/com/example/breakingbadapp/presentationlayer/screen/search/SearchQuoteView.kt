package com.example.breakingbadapp.presentationlayer.screen.search

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.datalayer.response.Quote
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchQuoteView : BaseFragmentView {
    fun displayQuotesList(quotes: List<Quote>)
    fun displayResponseError(text: String)
    fun displayNotFoundMessage()
}