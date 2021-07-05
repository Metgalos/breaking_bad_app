package com.example.breakingbadapp.di.module

import android.content.Context
import com.example.breakingbadapp.App
import com.example.breakingbadapp.core.ResourceProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApplication(): App = app

    @Provides
    @Singleton
    fun provideApplicationContext(app: App): Context =
        app.applicationContext

    @Provides
    @Singleton
    fun provideResourceProvider(context: Context): ResourceProvider =
        ResourceProvider(context)
}