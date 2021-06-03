package com.example.breakingbadapp.presentationlayer.screen.characters.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.breakingbadapp.presentationlayer.base.BaseFragment

class CharactersScreenSlideAdapter(
    fragment: BaseFragment,
    private val list: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}