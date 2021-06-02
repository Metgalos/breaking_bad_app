package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.databinding.FragmentRandomHistoryBinding
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RandomHistoryFragment : BaseFragment(), RandomHistoryView {

    @InjectPresenter
    lateinit var presenter: RandomHistoryPresenter

    private lateinit var binding: FragmentRandomHistoryBinding

    private val adapter by lazy {
        RandomHistoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomHistoryBinding.inflate(inflater, container, false)
        binding.characterResponseRecycleView.adapter = adapter
        binding.characterResponseRecycleView.layoutManager = LinearLayoutManager(requireContext())

        binding.characterResponseRecycleView.addOnScrollListener(presenter.getOnScrollListener())

        return binding.root
    }

    override fun addItems(characters: List<CharacterResponse>) {
        adapter.addItems(characters)
    }

    companion object {
        fun getScreen() = FragmentScreen { RandomHistoryFragment() }
    }
}