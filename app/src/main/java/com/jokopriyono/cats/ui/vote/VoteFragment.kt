package com.jokopriyono.cats.ui.vote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jokopriyono.cats.databinding.FragmentVoteBinding
import com.jokopriyono.cats.model.network.search.SearchResponseItem
import com.jokopriyono.cats.ui.MainActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope

@DelicateCoroutinesApi
class VoteFragment : Fragment(), VoteView {

    // https://api.thecatapi.com/v1/images/search?limit=1

    companion object {
        fun newInstance() = VoteFragment()
    }

    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!
    private var presenter: VotePresenterImp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = VotePresenterImp(this, GlobalScope)
    }

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

        refreshCat()

        binding.btnLoveIt.setOnClickListener {
            // love di postman value = 1
            presenter?.voteCat(true)
        }
        binding.btnNopeIt.setOnClickListener {
            // nope di postman value = 0
            presenter?.voteCat(false)
        }
    }

    override fun refreshCat() {
        presenter?.getCat()
    }

    override fun showCat(cat: SearchResponseItem) {
        context?.let { ctx ->
            (activity as MainActivity).hideLoading()
            Glide.with(ctx).load(cat.url).into(binding.imgCat)
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