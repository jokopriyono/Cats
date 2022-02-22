package com.jokopriyono.cats.model.vote


import com.google.gson.annotations.SerializedName

data class VoteResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String
)