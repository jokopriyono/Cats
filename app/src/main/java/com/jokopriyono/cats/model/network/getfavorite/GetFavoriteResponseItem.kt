package com.jokopriyono.cats.model.network.getfavorite


import com.google.gson.annotations.SerializedName

data class GetFavoriteResponseItem(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: Image,
    @SerializedName("image_id")
    val imageId: String,
    @SerializedName("sub_id")
    val subId: String,
    @SerializedName("user_id")
    val userId: String
)