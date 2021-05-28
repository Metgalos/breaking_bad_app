package com.example.breakingbadapp.domainlayer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.domainlayer.database.dao.CharacterResponseDao

@Database(entities = arrayOf(CharacterResponse::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterResponseDao(): CharacterResponseDao

    companion object {
        const val DB_NAME = "breaking_bad_db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val instance = instance

            if (instance != null) {
                return instance
            }

            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                Companion.instance = newInstance

                return newInstance
            }
        }
    }
}