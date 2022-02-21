package com.jokopriyono.cats.ui.vote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jokopriyono.cats.databinding.FragmentVoteBinding

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
}