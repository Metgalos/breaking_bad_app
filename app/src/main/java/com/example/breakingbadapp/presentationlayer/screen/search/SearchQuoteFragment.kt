package com.example.breakingbadapp.presentationlayer.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.databinding.FragmentSearchQuoteBinding
import com.example.breakingbadapp.datalayer.response.Quote
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.screen.search.adapter.QuoteListAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SearchQuoteFragment : BaseFragment(), SearchQuoteView {

    @InjectPresenter
    lateinit var presenter: SearchQuotePresenter

    private lateinit var binding: FragmentSearchQuoteBinding

    private val adapter: QuoteListAdapter by lazy {
        QuoteListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchQuoteBinding.inflate(inflater, container, false)

        visibleViews = listOf(
            binding.quotesRecycleview,
            binding.responseError,
            binding.messageText,
            binding.searchQuotesProgressbar
        )

        binding.quotesRecycleview.adapter = adapter
        binding.quotesRecycleview.layoutManager = LinearLayoutManager(requireContext())

        binding.searchQuotesButton.setOnClickListener {
            visibleOnly(binding.searchQuotesProgressbar)
            presenter.searchQuote(binding.characterNameEdit.text.toString())
        }

        binding.characterNameEdit.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    visibleOnly(binding.searchQuotesProgressbar)
                    presenter.searchQuote(v.text.toString())
                    return@setOnEditorActionListener true
                }
            }
            return@setOnEditorActionListener false
        }

        return binding.root
    }

    override fun displayQuotesList(quotes: List<Quote>) {
        adapter.setData(quotes)
        visibleOnly(binding.quotesRecycleview)
    }

    override fun displayResponseError(text: String) {
        visibleOnly(binding.responseError)
    }

    override fun displayNotFoundMessage() {
        visibleOnly(binding.messageText)
    }

    companion object {
        fun getScreen() = FragmentScreen {
            SearchQuoteFragment()
        }
    }
}
