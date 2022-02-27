package com.jokopriyono.cats.ui.breeds

import com.jokopriyono.cats.model.network.search.SearchResponseItem

interface BreedsPresenter {

    fun getCat(breedIds: String)

    fun updateCatDatabase(cats: List<SearchResponseItem>)

    fun getBreeds()

    fun postFavorite(position: Int, cat: SearchResponseItem)

}