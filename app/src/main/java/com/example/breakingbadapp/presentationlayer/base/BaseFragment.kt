package com.example.breakingbadapp.presentationlayer.base

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseFragmentView {

    protected open var visibleViews: List<View>? = null

    override fun displayMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun hideKeyboard() {
        activity?.let {
            val inputMethodManager = it.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.currentFocus?.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
        }
    }

    fun visibleOnly(view: View) {
        visibleViews?.forEach { visibleView ->
            visibleView.isVisible = (visibleView == view)
        }
    }
}