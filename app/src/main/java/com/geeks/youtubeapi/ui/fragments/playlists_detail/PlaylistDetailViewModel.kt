package com.geeks.youtubeapi.ui.fragments.playlists_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geeks.youtubeapi.data.model.BaseResponse
import com.geeks.youtubeapi.data.repository.YouTubeRepository
import com.geeks.youtubeapi.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PlaylistDetailViewModel(
    private val repository: YouTubeRepository
):ViewModel() {

    private val _playlistVideo = MutableLiveData<Resource<BaseResponse>>()
    val playlistVideo: LiveData<Resource<BaseResponse>> get() = _playlistVideo

    fun getPlaylistDetail(playlistId: String) {
        viewModelScope.launch {
            repository.getPlaylistItems(playlistId).observeForever {
                _playlistVideo.postValue(it)
            }
        }
    }

}