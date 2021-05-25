package com.example.breakingbadapp.di.module

import com.example.breakingbadapp.domainlayer.network.breakingbad.BreakingBadApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class BreakingBadApiModule {

    @Provides
    @Singleton
    fun provideBreakingBadApi(retrofitBuilder: Retrofit.Builder): BreakingBadApi =
        retrofitBuilder
            .baseUrl(BreakingBadApi.BASE_URL)
            .build()
            .create(BreakingBadApi::class.java)
}