package com.jokopriyono.cats.ui.vote

import com.jokopriyono.cats.model.SearchResponse
import com.jokopriyono.cats.model.SearchResponseItem
import com.jokopriyono.cats.model.vote.VoteBody
import com.jokopriyono.cats.model.vote.VoteResponse
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

    private var lastCat: SearchResponseItem? = null

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
                                val cat = response.body()!![0]
                                lastCat = cat
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

    override fun voteCat(loveOrNope: Boolean) {
        globalScope.launch(Dispatchers.IO) {
            val value = if (loveOrNope) 1 else 0
            val body = VoteBody(lastCat!!.id, "cats", value)
            ApiClient.instance.voteCat(body)
                .enqueue(object : Callback<VoteResponse> {
                    override fun onResponse(
                        call: Call<VoteResponse>,
                        response: Response<VoteResponse>
                    ) {
                        val code = response.code()
                        if (code == 200) {
                            val resultBody = response.body()!!
                            if (resultBody.message == "SUCCESS") {
                                view.refreshCat()
                            } else {
                                view.showError("Server menolak vote kamu")
                            }
                        } else {
                            view.showError("Masalah koneksi ke server $code")
                        }
                    }

                    override fun onFailure(call: Call<VoteResponse>, t: Throwable) {
                        view.showError("${t.localizedMessage}")
                    }

                })
        }
    }

}