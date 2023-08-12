package com.example.alomoves.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.alomoves.ui.classes.ClassesFragment
import com.example.alomoves.ui.overView.OverViewFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        // Return the number of pages/fragments
        return 2// For example, you can have 3 fragments
    }

    override fun createFragment(position: Int): Fragment {
        // Create and return a new instance of your fragment for each position
        return when (position) {
            0 -> OverViewFragment.newInstance()
            1 -> ClassesFragment.newInstance()
            else -> OverViewFragment.newInstance()
        }
    }
}