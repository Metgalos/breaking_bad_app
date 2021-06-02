package com.example.breakingbadapp.datalayer.model

import androidx.annotation.DrawableRes

data class LoadPhotoConfig(
    val url: String,
    @DrawableRes val placeholder: Int? = null
)