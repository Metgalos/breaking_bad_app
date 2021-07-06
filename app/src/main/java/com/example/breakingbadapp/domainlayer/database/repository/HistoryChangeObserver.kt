package com.example.breakingbadapp.domainlayer.database.repository

import com.example.breakingbadapp.datalayer.entity.CharacterResponse

interface HistoryChangeObserver {
    fun onDeleteItem(characterResponse: CharacterResponse)
}