package com.example.breakingbadapp.datalayer.response

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("id") var id: Int?,
    @SerializedName("quote") var quote: String?,
    @SerializedName("author") var author: String?,
    @SerializedName("series") var series: String?,
)