package com.example.breakingbadapp.presentationlayer.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseFragmentView {

    protected open var visibleViews: List<View>? = null

    @LayoutRes
    protected abstract fun getToolbarLayout(): Int

    @IdRes
    protected abstract fun getToolbarId(): Int

    @IdRes
    protected abstract fun getToolbarContainer(): Int

    override fun displayMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
        activity?.let {
            val inputMethodManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.currentFocus?.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }

    fun setTitle(title: String?) {
        view?.findViewById<Toolbar>(getToolbarId())?.title = title
    }

    fun setTitle(@StringRes titleResource: Int) {
        setTitle(getString(titleResource))
    }

    fun initToolbar() {
        layoutInflater.inflate(getToolbarLayout(), view?.findViewById(getToolbarContainer()), true)
    }

    fun initToolbarBackButton(callback: (View) -> Unit) {
        val toolbar = view?.findViewById<Toolbar>(getToolbarId())
        toolbar?.navigationIcon = ContextCompat.getDrawable(
            requireContext(),
            androidx.appcompat.R.drawable.abc_ic_ab_back_material
        )
        toolbar?.setNavigationOnClickListener(callback)
    }

    fun visibleOnly(view: View) {
        visibleViews?.forEach { visibleView ->
            visibleView.isVisible = (visibleView == view)
        }
    }
}