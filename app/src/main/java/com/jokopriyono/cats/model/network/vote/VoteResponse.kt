package com.jokopriyono.cats.model.network.vote


import com.google.gson.annotations.SerializedName

data class VoteResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String
)