package com.jokopriyono.cats.network

import com.jokopriyono.cats.model.network.search.SearchResponse
import com.jokopriyono.cats.model.network.breeds.BreedsResponse
import com.jokopriyono.cats.model.network.vote.VoteBody
import com.jokopriyono.cats.model.network.vote.VoteResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("images/search")
    fun searchImages(
        @Query("size") size: String? = null, // thumb , small, med, full
        @Query("mime_types") mimeType: String? = null, // jpg, png, gif
        @Query("format") format: String? = null, // json, src
        @Query("order") order: String? = null, // RANDOM, ASC, DESC
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null, // 1 - 25
        @Query("category_ids") categoryIds: String? = null,
        @Query("breed_ids") breedIds: String? = null,
        @Query("has_breeds") hasBreeds: Boolean? = null,
        @Query("include_breeds") includeBreeds: Boolean? = null,
        @Query("include_categories") includeCategories: Boolean? = null,
    ): Call<SearchResponse>

    @POST("votes")
    fun voteCat(
        @Body body: VoteBody
    ): Call<VoteResponse>

    @GET("breeds")
    fun getAllBreeds(
        @Query("limit") limit: Int? = null,
        @Query("page") page: Int? = null,
    ): Call<BreedsResponse>

}