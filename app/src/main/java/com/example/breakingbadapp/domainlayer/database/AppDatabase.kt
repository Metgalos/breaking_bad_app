package com.example.breakingbadapp.domainlayer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.dao.CharacterResponseDao

@Database(entities = [CharacterResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterResponseDao(): CharacterResponseDao

    companion object {
        const val DB_NAME = "breaking_bad_db"
    }
}