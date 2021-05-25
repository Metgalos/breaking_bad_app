package com.example.breakingbadapp.di.module

import com.example.breakingbadapp.domainlayer.service.imageloader.GlideImageLoader
import com.example.breakingbadapp.domainlayer.service.imageloader.ImageLoader
import dagger.Binds
import dagger.Module

@Module
interface BindModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader): ImageLoader
}