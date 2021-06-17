package com.example.breakingbadapp.presentationlayer.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.FragmentSearchQuoteBinding
import com.example.breakingbadapp.datalayer.response.Quote
import com.example.breakingbadapp.presentationlayer.base.BaseFragment
import com.example.breakingbadapp.presentationlayer.screen.search.adapter.QuoteListAdapter
import com.github.terrakok.cicerone.androidx.FragmentScreen

class SearchQuoteFragment : BaseFragment(), SearchQuoteView {

    @InjectPresenter
    lateinit var presenter: SearchQuotePresenter

    private lateinit var binding: FragmentSearchQuoteBinding

    private val viewAdapter: QuoteListAdapter by lazy {
        QuoteListAdapter()
    }

    override fun getToolbarContainer(): Int = R.id.toolbar_container

    override fun getToolbarId(): Int = R.id.toolbar

    override fun getToolbarLayout(): Int = R.layout.base_toolbar

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

        initRecycleView()
        setOnSearchListener()
        setOnEditorActionListener()

        return binding.root
    }

    private fun setOnEditorActionListener() {
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolbar()
        setTitle(R.string.search_quotes_toolbar_title)
    }

    private fun setOnSearchListener() {
        binding.searchQuotesButton.setOnClickListener {
            visibleOnly(binding.searchQuotesProgressbar)
            presenter.searchQuote(binding.characterNameEdit.text.toString())
        }
    }

    private fun initRecycleView() {
        binding.quotesRecycleview.apply {
            adapter = viewAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun displayQuotesList(quotes: List<Quote>) {
        viewAdapter.setData(quotes)
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
