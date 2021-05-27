package com.example.breakingbadapp.presentationlayer.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BaseFragmentView : MvpView {
    fun displayMessage(text: String)
    fun hideKeyboard()
}