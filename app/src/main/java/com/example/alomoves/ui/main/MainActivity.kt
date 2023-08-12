package com.example.alomoves.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.alomoves.R
import com.example.alomoves.databinding.ActivityMainBinding
import com.example.alomoves.ui.adapters.ViewPagerAdapter
import com.example.core.data.getData
import com.example.core.extensions.setImageFromUrl
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel.getOverView()

        viewPagerAdapter = ViewPagerAdapter(this)



        binding.vpCategory.adapter = viewPagerAdapter

        listOf("OVERVIEW", "CLASSES", "COMMUNITY").apply {
            TabLayoutMediator(binding.tabLayout, binding.vpCategory) { tab, postion ->
                tab.text = this[postion]
            }.attach()
        }



        viewModel.overView.observe(this) { resource ->
            val data = resource.getData()
            data?.let {
                binding.ivCover.setImageFromUrl(it.data.coverPhoto)
                binding.tvCover.text = it.data.seriesName
            }

        }

    }
}

