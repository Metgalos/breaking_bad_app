package com.example.breakingbadapp.presentationlayer.screen.main

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.App
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.ActivityMainBinding
import com.example.breakingbadapp.presentationlayer.base.BaseActivity
import com.github.terrakok.cicerone.Screen

class MainActivity : BaseActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private lateinit var binding: ActivityMainBinding

    @IdRes
    override fun getFragmentContainerId(): Int = R.id.fragment_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.appComponent.inject(this)

        binding.mainBottomNavigation.setOnNavigationItemSelectedListener { item ->
            return@setOnNavigationItemSelectedListener presenter.onBottomNavigationItemSelected(item.itemId)
        }
    }
}