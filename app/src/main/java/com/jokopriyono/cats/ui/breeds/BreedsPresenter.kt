package com.jokopriyono.cats.ui.breeds

interface BreedsPresenter {

    fun getCat(breedIds: String)

    fun getBreeds()

    fun postFavorite(imageId: String)

}