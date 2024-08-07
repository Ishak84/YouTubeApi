package com.geeks.youtubeapi.ui.fragments.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.geeks.youtubeapi.data.model.Item
import com.geeks.youtubeapi.data.repository.YouTubeRepository
import com.geeks.youtubeapi.utils.Resource

class PlayListsViewModel(
    private val repository: YouTubeRepository
) : ViewModel() {

    fun getPlayLists(): LiveData<Resource<List<Item>>> {
        return repository.getPlayLists()
    }
}