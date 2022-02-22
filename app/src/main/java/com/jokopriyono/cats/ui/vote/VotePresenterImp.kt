package com.jokopriyono.cats.ui.vote

import com.jokopriyono.cats.model.SearchResponse
import com.jokopriyono.cats.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VotePresenterImp(
    private val view: VoteView,
    private val globalScope: GlobalScope,
) : VotePresenter {
    override fun getCat() {
        globalScope.launch(Dispatchers.IO) {
            ApiClient.instance.searchImages(order = "RANDOM", limit = 1)
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
                                view.showCat(response.body()!![0])
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

    override fun voteCat() {
        // TODO("Not yet implemented")
    }

}