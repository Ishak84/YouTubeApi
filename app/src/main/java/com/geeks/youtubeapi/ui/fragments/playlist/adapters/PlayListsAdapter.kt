package com.geeks.youtubeapi.ui.fragments.playlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.geeks.youtubeapi.data.model.Item
import com.geeks.youtubeapi.databinding.ItemPlaylistsBinding
import com.geeks.youtubeapi.utils.OnClick

class PlaylistsAdapter(private val onClick: (Item) -> Unit) :
    ListAdapter<Item, PlaylistsAdapter.PlaylistViewHolder>(PlaylistItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(playlists: Item) {
            with(binding) {
                tvNamePlaylist.text = playlists.snippet.title
                tvQuantityVideo.text = playlists.contentDetails.itemCount.toString()
                imgPlaylists.load(playlists.snippet.thumbnails.medium.url)
                root.setOnClickListener {
                    onClick(playlists)
                }
            }
        }
    }
}

class PlaylistItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}