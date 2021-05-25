package com.example.breakingbadapp.di.module

import com.example.breakingbadapp.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideApplication(): App = app
}