package com.jokopriyono.cats.ui.breeds

import com.jokopriyono.cats.database.CatDatabase
import com.jokopriyono.cats.model.local.favorite.LocalFavorite
import com.jokopriyono.cats.model.network.breeds.BreedsResponse
import com.jokopriyono.cats.model.network.postfavorite.PostFavoriteBody
import com.jokopriyono.cats.model.network.postfavorite.PostFavoriteResponse
import com.jokopriyono.cats.model.network.search.SearchResponse
import com.jokopriyono.cats.model.network.search.SearchResponseItem
import com.jokopriyono.cats.network.ApiClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@DelicateCoroutinesApi
class BreedsPresenterImp(
    private val view: BreedsView,
    private val database: CatDatabase,
    private val globalScope: GlobalScope,
) : BreedsPresenter {

    override fun getCat(breedIds: String) {
        view.showLoading()
        globalScope.launch(Dispatchers.IO) {
            ApiClient.instance.searchImages(size = "small", breedIds = breedIds, limit = 10)
                .enqueue(object : Callback<SearchResponse> {
                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: Response<SearchResponse>
                    ) {
                        if (response.code() == 200) {
                            val cats = response.body()!!
                            if (cats.isEmpty()) {
                                view.showError("Tidak ada kucing di server")
                            } else {
                                val cat = response.body()!!
                                globalScope.launch {
                                    cat.forEach {
                                        val findInDatabase = database.favoriteDao()
                                            .findFavorite(it.id)
                                        if (findInDatabase.isNotEmpty()) {
                                            it.alreadySaved = true
                                        }
                                    }
                                }.also {
                                    view.showCats(cat)
                                }
                            }
                        } else {
                            view.showError("Masalah koneksi ke server ${response.code()}")
                        }
                        view.hideLoading()
                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        view.showError(t.message.toString())
                        view.hideLoading()
                    }
                })
        }
    }

    override fun getBreeds() {
        view.showLoading()
        globalScope.launch(Dispatchers.IO) {
            ApiClient.instance.getAllBreeds()
                .enqueue(object : Callback<BreedsResponse> {
                    override fun onResponse(
                        call: Call<BreedsResponse>,
                        response: Response<BreedsResponse>
                    ) {
                        if (response.code() == 200) {
                            val allBreeds = response.body()!!
                            if (allBreeds.isEmpty()) {
                                view.showError("Tidak ada breeds di server")
                            } else {
                                val breeds = response.body()!!
                                view.showAllBreeds(breeds)
                            }
                        } else {
                            view.showError("Masalah koneksi ke server ${response.code()}")
                        }
                        view.hideLoading()
                    }

                    override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
                        view.showError(t.message.toString())
                        view.hideLoading()
                    }

                })
        }
    }

    override fun postFavorite(position: Int, cat: SearchResponseItem) {
        view.showLoading()
        globalScope.launch(Dispatchers.IO) {
            val body = PostFavoriteBody(imageId = cat.id, subId = "joko")
            ApiClient.instance.postFavorite(body)
                .enqueue(object : Callback<PostFavoriteResponse> {
                    override fun onResponse(
                        call: Call<PostFavoriteResponse>,
                        response: Response<PostFavoriteResponse>
                    ) {
                        if (response.code() == 200) {
                            globalScope.launch {
                                val newCat = LocalFavorite(null, cat.id, cat.url)
                                database.favoriteDao().insertFavorite(newCat)
                            }.also {
                                view.updateItemRecycler(position)
                            }
                            view.showError("Sukses menambahkan favorit ke server")
                        } else {
                            view.showError("Masalah koneksi ke server ${response.code()}")
                        }
                        view.hideLoading()
                    }

                    override fun onFailure(call: Call<PostFavoriteResponse>, t: Throwable) {
                        view.showError(t.message.toString())
                        view.hideLoading()
                    }
                })
        }
    }

}