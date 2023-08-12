package com.example.alomoves.ui.overView

import android.content.res.AssetManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.viewModels
import com.example.alomoves.R
import com.example.alomoves.databinding.FragmentOverViewBinding
import com.example.alomoves.ui.base.BaseFragment
import com.example.core.extensions.setImageFromUrl
import com.example.core.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


@AndroidEntryPoint
class OverViewFragment :BaseFragment() {
    private lateinit var binding:FragmentOverViewBinding
    override val viewModel: OverViewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOverViewBinding.inflate(inflater,container,false)
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        viewModel.overView.observe(viewLifecycleOwner){resource->

            dataStateHandler(resource){overView ->
                binding.ivInstructor.setImageFromUrl(overView.data.coverPhoto)
                binding.tvOverView.text = overView.data.overView
                binding.tvInstructorName.text = overView.data.instructor.name
                binding.tvTotalRunTime.text=overView.data.instructor.status.runTime
                binding.tvAboutInstructor.text = overView.data.instructor.about
                binding.tvDefficulty.text=overView.data.instructor.status.difficulty
                binding.tvLevel.text=overView.data.instructor.status.intensity.toString()
                loadVideo(binding.videoView,overView.data.instructor.video)
            }
        }

        return binding.root
    }


    private fun loadVideo(videoView: VideoView, assetFileName: String) {
        val assetManager: AssetManager = requireContext().assets

        try {
            val inputStream: InputStream = assetManager.open(assetFileName)

            val tempFile = File(requireContext().cacheDir, assetFileName) // You can change the directory as needed
            tempFile.createNewFile()

            val outputStream = FileOutputStream(tempFile)
            inputStream.copyTo(outputStream)

            val assetFilePath = tempFile.absolutePath

            videoView.setVideoPath(assetFilePath)
            videoView.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    companion object {

        fun newInstance() =
            OverViewFragment()
    }
}
