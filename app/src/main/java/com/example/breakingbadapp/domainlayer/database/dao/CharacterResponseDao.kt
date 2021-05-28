package com.example.breakingbadapp.domainlayer.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.breakingbadapp.datalayer.entity.CharacterResponse

@Dao
interface CharacterResponseDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY datetime DESC")
    fun getAll(): List<CharacterResponse>

    @Insert
    fun insert(character: CharacterResponse)

    companion object {
        private const val TABLE_NAME = "character_responses"
    }
}