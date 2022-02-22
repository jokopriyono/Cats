package com.jokopriyono.cats.ui.vote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jokopriyono.cats.databinding.FragmentVoteBinding
import com.jokopriyono.cats.model.SearchResponse
import com.jokopriyono.cats.network.ApiClient
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VoteFragment : Fragment() {

    // https://api.thecatapi.com/v1/images/search?limit=1

    companion object {
        fun newInstance() = VoteFragment()
    }

    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            ApiClient.instance.searchImages("beng", true)
                .enqueue(object: Callback<SearchResponse> {
                    override fun onResponse(
                        call: Call<SearchResponse>,
                        response: Response<SearchResponse>
                    ) {
                        if (response.code() == 200) {
                            println("Get API searchImages sukses")
                        } else {
                            println("Get API searchImages gagal")
                        }
                    }

                    override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                        println("Get API searchImages error onFailure")
                    }
                })
        }
    }

}