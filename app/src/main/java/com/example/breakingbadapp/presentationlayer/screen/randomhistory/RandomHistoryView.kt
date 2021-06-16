package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.ConfirmationDialogOptions
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView

interface RandomHistoryView : BaseFragmentView {
    fun displayCharacters(characters: List<CharacterResponse>)
    fun displayEmptyHistoryText()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayConfirmation(options: ConfirmationDialogOptions)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideConfirmation()
}