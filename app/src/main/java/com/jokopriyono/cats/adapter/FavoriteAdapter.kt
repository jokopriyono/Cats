package com.jokopriyono.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jokopriyono.cats.databinding.ItemFavoriteCatBinding
import com.jokopriyono.cats.model.network.getfavorite.GetFavoriteResponseItem

class FavoriteAdapter(
    private val favorites: List<GetFavoriteResponseItem>,
    private val onItemClick: (GetFavoriteResponseItem) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemFavoriteCatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val view =
            ItemFavoriteCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        Glide.with(holder.binding.imgCat.context)
            .load(favorites[position].image.url)
            .into(holder.binding.imgCat)
        holder.binding.btnFavorite.setOnClickListener {
            onItemClick(favorites[position])
        }
    }

    override fun getItemCount() = favorites.size
}