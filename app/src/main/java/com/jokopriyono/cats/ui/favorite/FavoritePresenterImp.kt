package com.jokopriyono.cats.ui.favorite

import com.jokopriyono.cats.database.CatDatabase
import com.jokopriyono.cats.model.network.deletefavorite.DeleteFavoriteResponse
import com.jokopriyono.cats.model.network.getfavorite.GetFavoriteResponse
import com.jokopriyono.cats.network.ApiClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@DelicateCoroutinesApi
class FavoritePresenterImp(
    private val view: FavoriteView,
    private val database: CatDatabase,
    private val globalScope: GlobalScope
): FavoritePresenter {

    override fun getAllFavorite() {
        view.showLoading()
        globalScope.launch(Dispatchers.IO) {
            ApiClient.instance.getFavorite(subId = "joko")
                .enqueue(object : Callback<GetFavoriteResponse> {
                    override fun onResponse(
                        call: Call<GetFavoriteResponse>,
                        response: Response<GetFavoriteResponse>
                    ) {
                        if (response.code() == 200) {
                            view.showAllFavorite(response.body()!!)
                        } else {
                            view.showError("Masalah koneksi ke server ${response.code()}")
                        }
                        view.hideLoading()
                    }

                    override fun onFailure(call: Call<GetFavoriteResponse>, t: Throwable) {
                        view.showError(t.message.toString())
                        view.hideLoading()
                    }
                })
        }
    }

    override fun removeFavorite(favoriteId: Int) {
        view.showLoading()
        globalScope.launch(Dispatchers.IO) {
            ApiClient.instance.deleteFavorite(favoriteId = favoriteId)
                .enqueue(object : Callback<DeleteFavoriteResponse> {
                    override fun onResponse(
                        call: Call<DeleteFavoriteResponse>,
                        response: Response<DeleteFavoriteResponse>
                    ) {
                        view.hideLoading()
                        if (response.code() == 200) {
                            view.refreshFavorite()
                        } else {
                            view.showError("Masalah koneksi ke server ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<DeleteFavoriteResponse>, t: Throwable) {
                        view.showError(t.message.toString())
                        view.hideLoading()
                    }
                })
        }
    }
}