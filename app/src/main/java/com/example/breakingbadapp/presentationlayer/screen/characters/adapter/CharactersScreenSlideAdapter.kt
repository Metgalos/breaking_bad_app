package com.example.breakingbadapp.presentationlayer.screen.characters.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import com.example.breakingbadapp.presentationlayer.base.BaseFragment

class CharactersScreenSlideAdapter(
    fragment: BaseFragment,
    private var listener: CharactersScreenSlideAdapterListener?
) : FragmentStateAdapter(fragment) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        if (position + INDEX_FOR_LOAD == itemCount) {
            listener?.onLoading()
        }
        return fragments[position]
    }

    fun addItems(fragments: List<Fragment>) {
        val lastIndex = itemCount
        this.fragments.addAll(fragments)
        notifyItemRangeInserted(lastIndex, fragments.size)
    }

    fun onLoadingEnd() {
        listener = null
    }

    companion object {
        private const val INDEX_FOR_LOAD = 2
    }
}