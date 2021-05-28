package com.example.breakingbadapp.extension

import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import java.text.SimpleDateFormat
import java.util.*

fun SerialCharacter.toCharacterResponse(): CharacterResponse =
    with (this) {
        val datetime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US).format(Date())
        CharacterResponse(
            name, birthday, status, nickname, actor, picture, datetime
        )
    }
