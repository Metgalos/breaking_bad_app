package com.example.breakingbadapp.presentationlayer.screen.character_detail

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView

interface CharacterDetailView : BaseFragmentView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun displayCharacter(character: CharacterResponse)
}