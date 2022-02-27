package com.jokopriyono.cats.model.network.postfavorite


import com.google.gson.annotations.SerializedName

data class PostFavoriteResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String
)