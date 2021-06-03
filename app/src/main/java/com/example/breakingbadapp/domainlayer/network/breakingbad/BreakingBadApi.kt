package com.example.breakingbadapp.domainlayer.network.breakingbad

import com.example.breakingbadapp.datalayer.response.Quote
import com.example.breakingbadapp.datalayer.response.SerialCharacter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BreakingBadApi {
    companion object {
        const val BASE_URL = "https://www.breakingbadapi.com/api/"
    }

    @GET("character/random")
    fun getRandomCharacter(): Call<List<SerialCharacter>>

    @GET("quote")
    fun getQuoteByAuthor(
        @Query("author", encoded = true) author: String
    ): Call<List<Quote>>

    @GET("characters")
    fun getCharacters(): Call<List<SerialCharacter>>
}