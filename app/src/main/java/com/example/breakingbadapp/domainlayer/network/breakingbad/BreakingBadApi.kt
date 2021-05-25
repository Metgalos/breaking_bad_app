package com.example.breakingbadapp.domainlayer.network.breakingbad

import com.example.breakingbadapp.datalayer.response.SerialCharacter
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface BreakingBadApi {
    companion object {
        const val BASE_URL = "https://www.breakingbadapi.com/api/"
    }

    @GET("character/random")
    fun getRandomCharacter(): Call<List<SerialCharacter>>
}