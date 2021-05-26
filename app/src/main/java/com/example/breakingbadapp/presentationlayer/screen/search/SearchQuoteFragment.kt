package com.example.breakingbadapp.presentationlayer.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.databinding.FragmentSearchQuoteBinding
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SearchQuoteFragment : BaseFragment(), SearchQuoteView {

    @InjectPresenter
    lateinit var presenter: SearchQuotePresenter

    private lateinit var binding: FragmentSearchQuoteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun getScreen() = FragmentScreen {
            SearchQuoteFragment()
        }
    }
}