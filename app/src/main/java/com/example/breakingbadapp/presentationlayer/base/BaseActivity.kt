package com.example.breakingbadapp.presentationlayer.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.example.breakingbadapp.App
import com.example.breakingbadapp.presentationlayer.routing.AnimationNavigator
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class BaseActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var navigator: Navigator
    protected val app
        get() = (application as App)

    protected abstract fun getFragmentContainerId(): Int?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getFragmentContainerId()?.let { fragmentContainerId ->
            navigator = AnimationNavigator(this, fragmentContainerId)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}