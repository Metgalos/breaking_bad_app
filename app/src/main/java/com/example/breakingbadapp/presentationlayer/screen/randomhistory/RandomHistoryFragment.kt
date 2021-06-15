package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.databinding.FragmentRandomHistoryBinding
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.ConfirmationDialogOptions
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.dialog.ConfirmationDialogFragment
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RandomHistoryFragment : BaseFragment(), RandomHistoryView {

    @InjectPresenter
    lateinit var presenter: RandomHistoryPresenter

    private lateinit var binding: FragmentRandomHistoryBinding

    private val viewAdapter by lazy {
        RandomHistoryAdapter().also { it.setListener(presenter.getAdapterListener()) }
    }

    private var confirmationDialog: ConfirmationDialogFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomHistoryBinding.inflate(inflater, container, false)
        visibleViews = listOf(
            binding.characterResponseRecycleView,
            binding.emptyHistoryText
        )
        binding.characterResponseRecycleView.apply {
            this.adapter = viewAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            addOnScrollListener(presenter.getOnScrollListener())
        }

        return binding.root
    }

    override fun displayCharacters(characters: List<CharacterResponse>) {
        if (characters.isEmpty()) {
            displayEmptyHistoryText()
        } else {
            visibleOnly(binding.characterResponseRecycleView)
            viewAdapter.setData(characters.toList())
        }
    }

    override fun displayEmptyHistoryText() {
        visibleOnly(binding.emptyHistoryText)
    }

    override fun displayConfirmation(options: ConfirmationDialogOptions) {
        val listener = object : ConfirmationDialogFragment.Listener {
            override fun onPositiveAnswer() {
                presenter.clearHistory()
            }

            override fun onNegativeAnswer() {
                presenter.hideConfirmation()
            }
        }
        confirmationDialog = ConfirmationDialogFragment.newInstance(options, listener).also {
            it.show(requireFragmentManager(), CLEAR_CONFIRMATION_TAG)
        }
    }

    override fun hideConfirmation() {
        confirmationDialog?.dismiss()
    }

    companion object {
        private const val CLEAR_CONFIRMATION_TAG = "clearConfirmation"

        fun getScreen() = FragmentScreen { RandomHistoryFragment() }
    }
}