package com.jokopriyono.cats.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jokopriyono.cats.adapter.FavoriteAdapter
import com.jokopriyono.cats.database.CatDatabase
import com.jokopriyono.cats.databinding.FragmentFavoriteBinding
import com.jokopriyono.cats.model.network.getfavorite.GetFavoriteResponse
import com.jokopriyono.cats.ui.MainActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope

@DelicateCoroutinesApi
class FavoriteFragment : Fragment(), FavoriteView {

    // https://api.thecatapi.com/v1/favourites?limit=9&page=0&order=Asc&size=small

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private var presenter: FavoritePresenterImp? = null
    private var database: CatDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = CatDatabase.getInstance(requireContext())
        presenter = FavoritePresenterImp(this, database!!, GlobalScope)
    }

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

    override fun onDestroy() {
        super.onDestroy()
        CatDatabase.destroyInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerArray = arrayOf("Latest", "Oldest")
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, spinnerArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOrder.adapter = adapter
        binding.btnRefresh.setOnClickListener { refreshFavorite() }

        refreshFavorite()
    }

    override fun refreshFavorite() {
        presenter?.getAllFavorite()
    }

    override fun showAllFavorite(favorites: GetFavoriteResponse) {
        binding.recyclerFavCats.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = FavoriteAdapter(favorites) {
                presenter?.removeFavorite(it.id)
            }
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        (activity as MainActivity?)?.showLoading()
    }

    override fun hideLoading() {
        (activity as MainActivity?)?.hideLoading()
    }
}