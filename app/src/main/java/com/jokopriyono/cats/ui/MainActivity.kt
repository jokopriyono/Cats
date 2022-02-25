package com.jokopriyono.cats.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.jokopriyono.cats.R
import com.jokopriyono.cats.adapter.TabAdapter
import com.jokopriyono.cats.databinding.ActivityMainBinding
import com.jokopriyono.cats.dialog.CustomLoadingDialog

class MainActivity : AppCompatActivity(), BaseView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var loadingUI: CustomLoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingUI = CustomLoadingDialog(this)

        binding.viewPager.apply {
            this.adapter = TabAdapter(this@MainActivity)
        }
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.vote).uppercase()
                1 -> getString(R.string.breeds).uppercase()
                else -> getString(R.string.favorite).uppercase()
            }
        }.attach()

    }

    override fun showLoading() {
        loadingUI.show()
    }

    override fun hideLoading() {
        loadingUI.dismiss()
    }
}