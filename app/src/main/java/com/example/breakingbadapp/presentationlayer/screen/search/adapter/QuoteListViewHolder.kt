package com.example.breakingbadapp.presentationlayer.screen.search.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadapp.databinding.QuoteRowBinding
import com.example.breakingbadapp.datalayer.response.Quote

class QuoteListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = QuoteRowBinding.bind(itemView)

    fun bind(quote: Quote) {
        binding.quoteText.text = quote.quote
        binding.authorName.text = quote.author
    }
}