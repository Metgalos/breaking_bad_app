package com.example.breakingbadapp.di.module

import com.example.breakingbadapp.domainlayer.network.breakingbad.BreakingBadApi
import com.example.breakingbadapp.domainlayer.network.breakingbad.repository.CharacterRepository
import com.example.breakingbadapp.domainlayer.network.breakingbad.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import javax.inject.Singleton

@Module
class BreakingBadApiModule {

    @Provides
    @Singleton
    fun provideBreakingBadApi(retrofitBuilder: Retrofit.Builder): BreakingBadApi =
        retrofitBuilder
            .baseUrl(BreakingBadApi.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(BreakingBadApi::class.java)

    @Provides
    @Singleton
    fun provideCharacterRepository(api: BreakingBadApi): CharacterRepository =
        CharacterRepository(api)

    @Provides
    @Singleton
    fun provideQuoteRepository(api: BreakingBadApi): QuoteRepository =
        QuoteRepository(api)
}