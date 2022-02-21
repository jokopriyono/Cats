package com.jokopriyono.cats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jokopriyono.cats.databinding.ActivityMainBinding
import com.jokopriyono.cats.adapter.TabAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.apply {
            this.adapter = TabAdapter(this@MainActivity)
        }
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "VOTE"
                1 -> "SEARCH"
                else -> "FAVORITE"
            }
        }.attach()
    }
}