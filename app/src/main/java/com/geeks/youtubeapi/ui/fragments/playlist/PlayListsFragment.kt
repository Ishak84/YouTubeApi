package com.geeks.youtubeapi.ui.fragments.playlist

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.youtubeapi.R
import com.geeks.youtubeapi.databinding.FragmentPlayListsBinding
import com.geeks.youtubeapi.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.geeks.youtubeapi.ui.fragments.playlist.adapters.PlaylistsAdapter
import com.geeks.youtubeapi.utils.OnClick
import com.geeks.youtubeapi.utils.Resource

class PlayListsFragment : BaseFragment<FragmentPlayListsBinding>(FragmentPlayListsBinding::inflate),OnClick {

    private val viewModel: PlayListsViewModel by viewModel()
    private val playlistsAdapter: PlaylistsAdapter by lazy {
        PlaylistsAdapter { video ->
            navigateToPlaylistFragment(video.id)
        }
    }

    private fun navigateToPlaylistFragment(videoId: String) {
        val bundle = Bundle().apply {
            putString("ID", videoId)
        }
        findNavController().navigate(
            R.id.action_playListsFragment_to_playlistDetailFragment,
            bundle
        )
    }

    override fun setupViews() {
        super.setupViews()
        setupRecycler()
    }

    override fun setupObservers() {
        viewModel.getPlayLists().stateHandler(
            success = { data ->
                playlistsAdapter.submitList(data)
            },
            state = { state ->
                binding.pbLoading.isVisible = state is Resource.Loading
            }
        )
    }

    private fun setupRecycler() = with(binding.rvPlaylists) {
        adapter = playlistsAdapter
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClick(position: Int) {

    }
}