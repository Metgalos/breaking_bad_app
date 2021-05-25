package com.example.breakingbadapp

import android.app.Application
import com.example.breakingbadapp.di.component.AppComponent
import com.example.breakingbadapp.di.component.DaggerAppComponent
import com.example.breakingbadapp.di.module.AppModule
import com.github.terrakok.cicerone.Cicerone
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}