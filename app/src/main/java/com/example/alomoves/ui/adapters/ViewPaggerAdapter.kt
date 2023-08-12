package com.example.alomoves.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.alomoves.ui.overView.OverViewFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(
    fragmentManager,
    lifecycle
) {
    private val _data: MutableList<String> = mutableListOf()

    override fun getItemCount(): Int = _data.size

    override fun createFragment(position: Int): Fragment = when(position){
        0->    OverViewFragment.newInstance()
        else -> OverViewFragment.newInstance()
    }

}