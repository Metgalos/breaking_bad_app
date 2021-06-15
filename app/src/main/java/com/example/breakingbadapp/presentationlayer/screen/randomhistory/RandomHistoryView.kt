package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.presentationlayer.base.BaseFragmentView

interface RandomHistoryView : BaseFragmentView {
    fun displayCharacters(characters: List<CharacterResponse>)
}