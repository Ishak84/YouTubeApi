package com.geeks.youtubeapi.ui.fragments.playlists_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geeks.youtubeapi.data.model.Item
import com.geeks.youtubeapi.databinding.ItemPlaylistDetailBinding

class PlaylistDetailAdapter : ListAdapter<Item, PlaylistDetailAdapter.PlaylistViewHolder>(PlaylistItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = ItemPlaylistDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(playlist: Item) = with(binding) {
            tvTitle.text = playlist.snippet.title

            imgVideo.load(playlist.snippet.thumbnails.medium.url) {
                crossfade(true)
            }
        }
    }
}

class PlaylistItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}