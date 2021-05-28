package com.example.breakingbadapp.di.module

import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.database.AppDatabase
import com.example.breakingbadapp.domainlayer.database.dao.CharacterResponseDao
import com.example.breakingbadapp.domainlayer.database.repository.CharacterResponseRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(app: App): AppDatabase =
        AppDatabase.getDatabase(app.baseContext)

    @Provides
    @Singleton
    fun provideCharacterResponseDao(appDatabase: AppDatabase): CharacterResponseDao =
        appDatabase.characterResponseDao()

    @Provides
    @Singleton
    fun provideCharacterResponseRepository(dao: CharacterResponseDao): CharacterResponseRepository =
        CharacterResponseRepository(dao)
}