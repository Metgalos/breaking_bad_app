package com.example.breakingbadapp.presentationlayer.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.viewbinding.ViewBinding
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.breakingbadapp.R
import com.example.breakingbadapp.databinding.BaseFragmentBinding

abstract class BaseFragment<Binding : ViewBinding> : MvpAppCompatFragment(), BaseFragmentView {

    protected open var visibleViews: List<View>? = null

    private lateinit var rootBinding: BaseFragmentBinding

    protected lateinit var binding: Binding

    protected abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    @LayoutRes
    protected abstract fun getToolbarLayout(): Int

    @IdRes
    protected abstract fun getToolbarId(): Int

    @IdRes
    protected abstract fun getToolbarContainer(): Int

    override fun displayMessage(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootBinding = BaseFragmentBinding.inflate(inflater, container, false)
        binding = bindingInflater.invoke(inflater, rootBinding.contentContainer, true)
        return rootBinding.root
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

    fun initToolbarBackButton() {
        val toolbar = view?.findViewById<Toolbar>(getToolbarId())
        toolbar?.navigationIcon = ContextCompat.getDrawable(
            requireContext(),
            androidx.appcompat.R.drawable.abc_ic_ab_back_material
        )
        toolbar?.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    fun visibleOnly(view: View) {
        visibleViews?.forEach { visibleView ->
            visibleView.isVisible = (visibleView == view)
        }
    }
}