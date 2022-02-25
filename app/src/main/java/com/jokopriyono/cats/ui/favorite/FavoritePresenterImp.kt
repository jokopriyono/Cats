package com.jokopriyono.cats.ui.favorite

import kotlinx.coroutines.GlobalScope

class FavoritePresenterImp(
    private val view: FavoriteView,
    private val globalScope: GlobalScope
): FavoritePresenter {
    override fun getAllFavorite() {
        view.showLoading()
        view.hideLoading()
        // TODO
    }

    override fun removeFavorite() {
        view.showLoading()
        view.hideLoading()
        // TODO
    }
}