package com.example.breakingbadapp.presentationlayer.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.breakingbadapp.databinding.FragmentRandomCharacterBinding

abstract class BaseFragment : MvpAppCompatFragment(), BaseFragmentView {

    protected lateinit var binding: FragmentRandomCharacterBinding

    @LayoutRes
    abstract fun getRootViewLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun displayMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}