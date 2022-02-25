package com.jokopriyono.cats.ui.favorite

import com.jokopriyono.cats.ui.BaseView

interface FavoriteView: BaseView {

    fun refreshFavorite()

    fun showAllFavorite()

    fun showError(message: String)

}