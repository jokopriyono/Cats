package com.jokopriyono.cats.ui.favorite

import com.jokopriyono.cats.model.network.getfavorite.GetFavoriteResponse
import com.jokopriyono.cats.ui.BaseView

interface FavoriteView: BaseView {

    fun refreshFavorite()

    fun showAllFavorite(favorites: GetFavoriteResponse)

    fun showError(message: String)

}