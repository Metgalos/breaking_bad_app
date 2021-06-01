package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView

@StateStrategyType(AddToEndSingleStrategy::class)
interface RandomHistoryView : BaseFragmentView {
    fun addItems(characters: List<CharacterResponse>)
}