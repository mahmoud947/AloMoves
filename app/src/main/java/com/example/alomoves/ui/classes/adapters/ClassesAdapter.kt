package com.example.alomoves.ui.classes.adapters

import android.content.Context
import android.content.res.AssetManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.alomoves.databinding.ItemClassesVideoBinding
import com.example.data.models.response.Video
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import java.io.IOException

class ClassesAdapter() : ListAdapter<Video, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Video>() {

            override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean =
                oldItem.title == newItem.title

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ClassViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ClassViewHolder -> {
                val item = getItem(position)
                holder.bind(item)
            }
        }
    }


    class ClassViewHolder private constructor(
        private val binding: ItemClassesVideoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var player: SimpleExoPlayer? = null

        init {
            binding.classVideoView.player = player
        }

        fun bind(item: Video) {
            binding.tvDescription.text = item.description
            binding.tvTime.text = item.time
            binding.tvTitle.text = item.title

            releasePlayer() // Release previous player instance if any

            player = SimpleExoPlayer.Builder(binding.classVideoView.context).build()
            loadVideo(player!!, item.video, binding.classVideoView.context)
            binding.classVideoView.setOnClickListener {
                player!!.play()
            }
        }

        fun releasePlayer() {
            player?.release()
            player = null
        }

        private fun loadVideo(player: SimpleExoPlayer, assetFileName: String, context: Context) {
            val assetManager: AssetManager = context.assets

            try {
                val dataSourceFactory = DefaultDataSourceFactory(
                    context,
                    Util.getUserAgent(context, "AloMoves")
                )

                val mediaItem = MediaItem.fromUri(
                    Uri.parse("asset:///$assetFileName")
                ) // Use asset scheme

                val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaItem)

                player.setMediaSource(mediaSource)
                player.prepare()

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }


        companion object {
            fun from(viewGroup: ViewGroup): ClassViewHolder {
                val bind = ItemClassesVideoBinding.inflate(
                    LayoutInflater.from(viewGroup.context),
                    viewGroup,
                    false
                )
                return ClassViewHolder(bind)
            }
        }


    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        if (holder is ClassViewHolder)
            holder.releasePlayer()
        super.onViewRecycled(holder)
    }
}