package com.example.breakingbadapp.presentationlayer.screen.random

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView

@StateStrategyType(AddToEndSingleStrategy::class)
interface RandomCharacterView : BaseFragmentView {
    fun displayRandomCharacter(serialCharacter: SerialCharacter)
    fun hideCharacter()
    fun showCharacter()
}