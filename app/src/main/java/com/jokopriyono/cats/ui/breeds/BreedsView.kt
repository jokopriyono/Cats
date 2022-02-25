package com.jokopriyono.cats.ui.breeds

import com.jokopriyono.cats.model.SearchResponseItem
import com.jokopriyono.cats.model.breeds.BreedsResponse

interface BreedsView {

    fun refreshCat(breedsId: String)

    fun showCat(cats: ArrayList<SearchResponseItem>)

    fun showError(message: String)

    fun showAllBreeds(breeds: BreedsResponse)

}