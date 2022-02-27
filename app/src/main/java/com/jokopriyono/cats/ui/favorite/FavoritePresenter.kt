package com.jokopriyono.cats.ui.favorite

import com.jokopriyono.cats.model.network.getfavorite.GetFavoriteResponseItem

interface FavoritePresenter {

    fun getAllFavorite()

    fun removeFavorite(favorite: GetFavoriteResponseItem)

}