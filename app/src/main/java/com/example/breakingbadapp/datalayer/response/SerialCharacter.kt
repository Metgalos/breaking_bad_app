package com.example.breakingbadapp.datalayer.response

import com.google.gson.annotations.SerializedName

data class SerialCharacter(
    @SerializedName("name") var name: String?,
    @SerializedName("birthday") var birthday: String?,
    @SerializedName("status") var status: String?,
    @SerializedName("nickname") var nickname: String?,
    @SerializedName("portrayed") var actor: String?,
    @SerializedName("picture") var picture: String?
)