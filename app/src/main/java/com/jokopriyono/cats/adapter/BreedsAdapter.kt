package com.jokopriyono.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jokopriyono.cats.databinding.ItemCatBinding
import com.jokopriyono.cats.model.SearchResponseItem

class BreedsAdapter(
    private val cats: ArrayList<SearchResponseItem>,
    private val onItemClick: (SearchResponseItem) -> Unit
) : RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsAdapter.ViewHolder {
        val view = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedsAdapter.ViewHolder, position: Int) {
        Glide.with(holder.binding.imgCat.context)
            .load(cats[position].url)
            .into(holder.binding.imgCat)
    }

    override fun getItemCount() = cats.size
}