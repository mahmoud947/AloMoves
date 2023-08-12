package com.example.alomoves.ui.overView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.alomoves.R
import com.example.alomoves.databinding.FragmentOverViewBinding
import com.example.alomoves.ui.base.BaseFragment
import com.example.alomoves.ui.base.BaseViewModel


class OverViewFragment :BaseFragment() {
    private lateinit var binding:FragmentOverViewBinding
    override val viewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOverViewBinding.inflate(inflater,container,false)
        return binding.root
    }

    companion object {

        fun newInstance() =
            OverViewFragment()
    }
}
