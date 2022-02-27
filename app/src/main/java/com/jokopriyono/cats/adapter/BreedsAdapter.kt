package com.jokopriyono.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jokopriyono.cats.R
import com.jokopriyono.cats.databinding.ItemCatBinding
import com.jokopriyono.cats.model.network.search.SearchResponseItem

class BreedsAdapter(
    private val cats: ArrayList<SearchResponseItem>,
    private val onItemClick: (Int, SearchResponseItem) -> Unit
) : RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsAdapter.ViewHolder {
        val view = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedsAdapter.ViewHolder, position: Int) {
        val cat = cats[position]
        holder.binding.root.context.let {
            if (cat.alreadySaved)
                holder.binding.btnFavorite.icon =
                    ContextCompat.getDrawable(it, R.drawable.ic_favorite)
            else
                holder.binding.btnFavorite.icon =
                    ContextCompat.getDrawable(it, R.drawable.ic_unfavorite)
        }
        holder.binding.btnFavorite.setOnClickListener { onItemClick(position, cat) }
        Glide.with(holder.binding.imgCat.context)
            .load(cat.url)
            .into(holder.binding.imgCat)
    }

    fun updateItem(position: Int, alreadySaved: Boolean) {
        val cat = cats[position]
        cat.alreadySaved = alreadySaved
        notifyItemChanged(position)
    }

    override fun getItemCount() = cats.size
}