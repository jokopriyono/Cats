package com.jokopriyono.cats.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jokopriyono.cats.adapter.FavoriteAdapter
import com.jokopriyono.cats.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    // https://api.thecatapi.com/v1/favourites?limit=9&page=0&order=Asc&size=small

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerFavCats.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = FavoriteAdapter()
        }
    }
}