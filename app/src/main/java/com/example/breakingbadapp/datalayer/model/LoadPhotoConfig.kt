package com.example.breakingbadapp.datalayer.model

import androidx.annotation.IdRes

data class LoadPhotoConfig(
    val url: String,
    @IdRes val placeholder: Int? = null
)