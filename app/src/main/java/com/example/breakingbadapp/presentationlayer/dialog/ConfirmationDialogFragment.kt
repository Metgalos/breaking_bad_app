package com.example.breakingbadapp.presentationlayer.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breakingbadapp.databinding.ConfirmationDialogBinding
import com.example.breakingbadapp.datalayer.model.ConfirmationDialogOptions
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ConfirmationDialogFragment(
    private val options: ConfirmationDialogOptions,
    private val listener: Listener
) : BottomSheetDialogFragment() {

    private lateinit var binding: ConfirmationDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConfirmationDialogBinding.inflate(inflater, container, false)
        with(binding) {
            confirmationText.text = getString(options.message)
            positiveButton.text = getString(options.positiveText)
            negativeButton.text = getString(options.negativeText)

            positiveButton.setOnClickListener { listener.onPositiveAnswer() }
            negativeButton.setOnClickListener { listener.onNegativeAnswer() }
        }
        return binding.root
    }

    interface Listener {
        fun onPositiveAnswer()
        fun onNegativeAnswer()
    }

    companion object {
        fun newInstance(
            options: ConfirmationDialogOptions,
            listener: Listener
        ): ConfirmationDialogFragment = ConfirmationDialogFragment(options, listener)
    }
}