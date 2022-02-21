package com.jokopriyono.cats.ui.breeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jokopriyono.cats.databinding.FragmentBreedsBinding

class BreedsFragment : Fragment() {

    // https://api.thecatapi.com/v1/images/search?limit=8&size=full&breed_id=abob

    companion object {
        fun newInstance() = BreedsFragment()
    }

    private var _binding: FragmentBreedsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBreedsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}