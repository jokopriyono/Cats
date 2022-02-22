package com.jokopriyono.cats.model.vote

import com.google.gson.annotations.SerializedName

data class VoteBody(
    @SerializedName("image_id")
    val imageId: String,
    @SerializedName("sub_id")
    val subId: String,
    @SerializedName("value")
    val value: Int,
)
