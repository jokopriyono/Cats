package com.jokopriyono.cats.ui.favorite

import com.jokopriyono.cats.database.CatDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope

@DelicateCoroutinesApi
class FavoritePresenterImp(
    private val view: FavoriteView,
    private val database: CatDatabase,
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