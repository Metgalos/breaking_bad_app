package com.example.breakingbadapp.presentationlayer.base

import com.arellomobile.mvp.MvpView

interface BaseFragmentView : MvpView {
    fun displayMessage(text: String)
}