package com.jokopriyono.cats.ui.breeds

import com.jokopriyono.cats.model.network.search.SearchResponse
import com.jokopriyono.cats.model.network.breeds.BreedsResponse
import com.jokopriyono.cats.ui.BaseView

interface BreedsView: BaseView {

    fun refreshCat()

    fun showCats(cats: SearchResponse)

    fun showError(message: String)

    fun showAllBreeds(breeds: BreedsResponse)

    fun updateItemRecycler(position: Int)

}