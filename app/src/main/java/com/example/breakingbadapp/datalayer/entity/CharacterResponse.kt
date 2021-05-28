package com.example.breakingbadapp.datalayer.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.ZonedDateTime

@Entity(tableName = "character_responses")
data class CharacterResponse(
    val name: String?,
    val birthday: String?,
    val status: String?,
    val nickname: String?,
    val actor: String?,
    val picture_url: String?,
    val datetime: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)