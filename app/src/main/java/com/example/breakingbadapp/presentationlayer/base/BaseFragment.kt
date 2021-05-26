package com.example.breakingbadapp.presentationlayer.base

import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseFragmentView {

    override fun displayMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}