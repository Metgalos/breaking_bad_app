package com.example.breakingbadapp.presentationlayer.screen.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.domainlayer.network.breakingbad.BreakingBadApi
import com.example.breakingbadapp.presentationlayer.screen.random.RandomCharacterFragment
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        App.appComponent.inject(this)
        loadInitialScreen()
    }

    private fun loadInitialScreen() {
        router.replaceScreen(RandomCharacterFragment.getScreen())
    }
}