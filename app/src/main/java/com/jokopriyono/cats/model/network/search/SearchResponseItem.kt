package com.jokopriyono.cats.model.network.search


import com.google.gson.annotations.SerializedName

data class SearchResponseItem(
    @SerializedName("breeds")
    val breeds: List<Breed>,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int,

    // custom properties
    var alreadySaved: Boolean = false,
)