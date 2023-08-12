package com.example.alomoves.ui.classes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.alomoves.R
import com.example.alomoves.databinding.FragmentClassesBinding
import com.example.alomoves.ui.base.BaseFragment
import com.example.alomoves.ui.classes.adapters.ClassesAdapter
import com.example.core.extensions.toast
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ClassesFragment : BaseFragment() {
    private lateinit var binding: FragmentClassesBinding
    override val viewModel: ClassesViewModel by viewModels()
    private lateinit var classesAdapter: ClassesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClassesBinding.inflate(inflater, container, false)

        classesAdapter = ClassesAdapter()

        binding.rvClasses.adapter = classesAdapter

        viewModel.classes.observe(viewLifecycleOwner) { resource ->
            dataStateHandler(resource) { classes ->
                classesAdapter.submitList(classes.data.videos)
            }
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ClassesFragment()
    }
}