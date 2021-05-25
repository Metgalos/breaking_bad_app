package com.example.breakingbadapp.domainlayer.service.imageloader

import android.widget.ImageView
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig

interface ImageLoader {
    fun load(config: LoadPhotoConfig, imageView: ImageView)
}