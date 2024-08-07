package com.geeks.youtubeapi.ui.fragments.playlists_detail

import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.youtubeapi.databinding.FragmentPlaylistDetailBinding
import com.geeks.youtubeapi.ui.base.BaseFragment
import com.geeks.youtubeapi.ui.fragments.playlists_detail.adapter.PlaylistDetailAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistDetailFragment : BaseFragment<FragmentPlaylistDetailBinding>(FragmentPlaylistDetailBinding::inflate) {

    private val viewModel by viewModel<PlaylistDetailViewModel>()
    private val playlistAdapter: PlaylistDetailAdapter by lazy { PlaylistDetailAdapter() }

    override fun getArgs() {
        val videoId = arguments?.getString("ID") ?: return
        viewModel.getPlaylistDetail(videoId)
    }

    override fun setupViews() {
        super.setupViews()
        setupRecyclerView()
    }

    override fun initClickListeners() {
        super.initClickListeners()
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.playlistVideo.handleResource(
            onSuccess = { data ->
                if (data != null) {
                    playlistAdapter.submitList(data.items)
                }
                if (data != null) {
                    if (data.items.isNotEmpty()) {
                        val item = data.items[0]
                        binding.tvTitle.text = item.snippet.title
                        binding.tvDescription.text = item.snippet.description
                       // binding..text = "${data.items.size} video series"
                    }
                }
            },
            isLoading = { visibility ->
                binding.pbLoading.isVisible = visibility
            }
        )
    }

    private fun setupRecyclerView() = with(binding.rvVideo) {
        layoutManager = LinearLayoutManager(context)
        adapter = playlistAdapter
    }
}