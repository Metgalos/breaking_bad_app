package com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter

import com.example.breakingbadapp.datalayer.entity.CharacterResponse

interface RandomHistoryViewHolderListener {
    fun onDeleteItem(character: CharacterResponse)
}