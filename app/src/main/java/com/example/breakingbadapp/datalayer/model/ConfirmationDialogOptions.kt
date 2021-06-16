package com.example.breakingbadapp.datalayer.model

import androidx.annotation.StringRes
import com.example.breakingbadapp.R

data class ConfirmationDialogOptions(
    @StringRes val message: Int = R.string.default_confirmation_message,
    @StringRes val positiveText: Int = R.string.confirm,
    @StringRes val negativeText: Int = R.string.cancel,
)