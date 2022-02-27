package com.jokopriyono.cats.network

import com.jokopriyono.cats.model.network.search.SearchResponse
import com.jokopriyono.cats.model.network.breeds.BreedsResponse
import com.jokopriyono.cats.model.network.deletefavorite.DeleteFavoriteResponse
import com.jokopriyono.cats.model.network.getfavorite.GetFavoriteResponse
import com.jokopriyono.cats.model.network.postfavorite.PostFavoriteBody
import com.jokopriyono.cats.model.network.postfavorite.PostFavoriteResponse
import com.jokopriyono.cats.model.network.vote.VoteBody
import com.jokopriyono.cats.model.network.vote.VoteResponse
import retrofit2.Call
import retrofit2.http.*

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


    @POST("favourites")
    fun postFavorite(
        @Body body: PostFavoriteBody
    ): Call<PostFavoriteResponse>

    @GET("favourites")
    fun getFavorite(
        @Query("sub_id") subId: String,
    ): Call<GetFavoriteResponse>

    @DELETE("favourites/{favourite_id}")
    fun deleteFavorite(
        @Path("favourite_id") favoriteId: Int
    ): Call<DeleteFavoriteResponse>

}