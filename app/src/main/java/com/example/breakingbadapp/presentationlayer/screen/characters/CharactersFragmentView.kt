package com.example.breakingbadapp.presentationlayer.screen.characters

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView

@StateStrategyType(AddToEndSingleStrategy::class)
interface CharactersFragmentView : BaseFragmentView {
    fun displayCharacters(characters: List<SerialCharacter>)
    fun hideProgressBar()
}