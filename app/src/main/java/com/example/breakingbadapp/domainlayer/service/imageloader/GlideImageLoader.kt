package com.example.breakingbadapp.domainlayer.service.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.breakingbadapp.datalayer.model.LoadPhotoConfig

class GlideImageLoader : ImageLoader {
    override fun load(config: LoadPhotoConfig, imageView: ImageView) {
        Glide.with(imageView.context)
            .load(config.url)
            .placeholder(config.placeholder ?: -1)
            .error(config.placeholder ?: -1)
            .into(imageView)
    }
}