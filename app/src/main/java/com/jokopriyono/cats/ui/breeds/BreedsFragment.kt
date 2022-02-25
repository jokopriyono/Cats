package com.jokopriyono.cats.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.jokopriyono.cats.adapter.BreedsAdapter
import com.jokopriyono.cats.databinding.FragmentBreedsBinding
import com.jokopriyono.cats.model.SearchResponse
import com.jokopriyono.cats.model.breeds.BreedsResponse
import com.jokopriyono.cats.ui.MainActivity
import kotlinx.coroutines.GlobalScope

class BreedsFragment : Fragment(), BreedsView {

    // https://api.thecatapi.com/v1/images/search?limit=8&size=full&breed_id=abob

    companion object {
        fun newInstance() = BreedsFragment()
    }

    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!
    private var presenter: BreedsPresenterImp? = null

    private var lastSelectedBreeds = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = BreedsPresenterImp(this, GlobalScope)
        presenter?.getBreeds()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRefresh.setOnClickListener {
            refreshCat()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun refreshCat() {
        if (lastSelectedBreeds.isNotEmpty()) {
            presenter?.getCat(lastSelectedBreeds)
        }
    }

    override fun showCats(cats: SearchResponse) {
        binding.recyclerCats.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = BreedsAdapter(cats)
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showAllBreeds(breeds: BreedsResponse) {
        val spinnerArray = breeds.map { it.name } // convert array object to array string
        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_item, spinnerArray
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerBreeds.adapter = adapter
        lastSelectedBreeds = breeds[binding.spinnerBreeds.selectedItemPosition].id

        binding.spinnerBreeds.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    selectedItemView: View?,
                    pos: Int,
                    id: Long
                ) {
                    lastSelectedBreeds = breeds[pos].id
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    lastSelectedBreeds = ""
                }

            }
        refreshCat()
    }

    override fun showLoading() {
        (activity as MainActivity?)?.showLoading()
    }

    override fun hideLoading() {
        (activity as MainActivity?)?.hideLoading()
    }
}