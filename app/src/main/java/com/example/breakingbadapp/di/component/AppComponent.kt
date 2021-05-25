package com.example.breakingbadapp.di.component

import com.example.breakingbadapp.di.module.*
import com.example.breakingbadapp.presentationlayer.screen.main.MainActivity
import com.example.breakingbadapp.presentationlayer.screen.main.MainPresenter
import com.example.breakingbadapp.presentationlayer.screen.random.RandomCharacterFragment
import com.example.breakingbadapp.presentationlayer.screen.random.RandomCharacterPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    NavigationModule::class,
    AppModule::class,
    BreakingBadApiModule::class,
    NetworkModule::class,
    BindModule::class
])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

    fun inject(randomCharacterFragment: RandomCharacterFragment)
    fun inject(randomCharacterPresenter: RandomCharacterPresenter)
}