package com.jokopriyono.cats.ui.breeds

import com.jokopriyono.cats.model.SearchResponse
import com.jokopriyono.cats.model.breeds.BreedsResponse
import com.jokopriyono.cats.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedsPresenterImp(
    private val view: BreedsView,
    private val globalScope: GlobalScope,
) : BreedsPresenter {

    override fun getCat(breedIds: String) {
        globalScope.launch(Dispatchers.IO) {
            ApiClient.instance.searchImages(breedIds = breedIds, limit = 10)
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
                                view.showCat(cat)
                            }
                        } else {
                            view.showError("Masalah koneksi ke server ${response.code()}")
                        }
                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        view.showError("${t.localizedMessage}")
                    }
                })
        }
    }

    override fun getBreeds() {
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
                    }

                    override fun onFailure(call: Call<BreedsResponse>, t: Throwable) {
                        view.showError("${t.localizedMessage}")
                    }

                })
        }
    }

}