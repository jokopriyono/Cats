package com.jokopriyono.cats.network

import com.jokopriyono.cats.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("images/search")
    fun searchImages(
        @Query("breed_ids") breedIds: String,
        @Query("include_breeds") includeBreeds: Boolean
    ): Call<SearchResponse>

}