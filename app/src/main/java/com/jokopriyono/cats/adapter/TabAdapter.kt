package com.jokopriyono.cats.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jokopriyono.cats.ui.favorite.FavoriteFragment
import com.jokopriyono.cats.ui.breeds.BreedsFragment
import com.jokopriyono.cats.ui.vote.VoteFragment
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class TabAdapter(fragmentAct: FragmentActivity) : FragmentStateAdapter(fragmentAct) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VoteFragment.newInstance()
            1 -> BreedsFragment.newInstance()
            else -> FavoriteFragment.newInstance()
        }
    }
}