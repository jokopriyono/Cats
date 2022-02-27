package com.jokopriyono.cats.model.network.deletefavorite


import com.google.gson.annotations.SerializedName

data class DeleteFavoriteResponse(
    @SerializedName("message")
    val message: String
)