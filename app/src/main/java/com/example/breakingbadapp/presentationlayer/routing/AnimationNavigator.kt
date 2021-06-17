package com.example.breakingbadapp.presentationlayer.routing

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AnimationNavigator(
    activity: FragmentActivity,
    @IdRes containerId: Int
) : AppNavigator(activity, containerId) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        val transaction = if (currentFragment != null && currentFragment::class == nextFragment::class) {
            fragmentTransaction
        } else {
            fragmentTransaction.setCustomAnimations(
                androidx.appcompat.R.anim.abc_slide_in_top,
                androidx.appcompat.R.anim.abc_popup_exit,
                androidx.appcompat.R.anim.abc_slide_in_bottom,
                androidx.appcompat.R.anim.abc_popup_exit,
            )
        }

        super.setupFragmentTransaction(
            screen,
            transaction,
            currentFragment,
            nextFragment)
    }
}