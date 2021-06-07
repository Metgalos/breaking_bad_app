package com.example.breakingbadapp.presentationlayer.screen.characters

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView
import com.example.breakingbadapp.presentationlayer.screen.character.CharacterFragment

interface CharactersFragmentView : BaseFragmentView {

    fun addCharacters(characters: List<CharacterFragment>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun hideProgressBar()
}