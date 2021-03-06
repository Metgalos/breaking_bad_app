package com.example.breakingbadapp.presentationlayer.screen.randomhistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.FragmentRandomHistoryBinding
import com.example.breakingbadapp.datalayer.entity.CharacterResponse
import com.example.breakingbadapp.datalayer.model.ConfirmationDialogOptions
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.dialog.ConfirmationDialogFragment
import com.example.breakingbadapp.presentationlayer.screen.randomhistory.adapter.RandomHistoryAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class RandomHistoryFragment : BaseFragment<FragmentRandomHistoryBinding>(), RandomHistoryView {

    @InjectPresenter
    lateinit var presenter: RandomHistoryPresenter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRandomHistoryBinding
        get() = FragmentRandomHistoryBinding::inflate

    private val viewAdapter by lazy {
        RandomHistoryAdapter().also { it.setListener(presenter.getAdapterListener()) }
    }

    private var confirmationDialog: ConfirmationDialogFragment? = null

    override fun getToolbarContainer(): Int = R.id.toolbar_container

    override fun getToolbarId(): Int = R.id.toolbar

    override fun getToolbarLayout(): Int = R.layout.base_toolbar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        setTitle(R.string.history_toolbar_title)

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
                hideConfirmation()
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