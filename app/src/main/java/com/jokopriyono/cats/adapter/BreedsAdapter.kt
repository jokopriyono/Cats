package com.jokopriyono.cats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jokopriyono.cats.databinding.ItemCatBinding

class BreedsAdapter : RecyclerView.Adapter<BreedsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemCatBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsAdapter.ViewHolder {
        val view = ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedsAdapter.ViewHolder, position: Int) {
        // TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        // TODO("Not yet implemented")
        return 10
    }
}