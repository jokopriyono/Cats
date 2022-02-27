package com.jokopriyono.cats.model.network.postfavorite

import com.google.gson.annotations.SerializedName

data class PostFavoriteBody(
    @SerializedName("image_id")
    val imageId: String,
    @SerializedName("sub_id")
    val subId: String,
)
