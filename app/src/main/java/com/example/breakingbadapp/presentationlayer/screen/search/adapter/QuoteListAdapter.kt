package com.example.breakingbadapp.presentationlayer.screen.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breakingbadapp.R
import com.example.breakingbadapp.datalayer.response.Quote

class QuoteListAdapter : RecyclerView.Adapter<QuoteListViewHolder>() {

    private var dataSet: List<Quote> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quote_row, parent, false)
        return QuoteListViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteListViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size

    fun setData(quotes: List<Quote>) {
        dataSet = quotes
        notifyDataSetChanged()
    }
}