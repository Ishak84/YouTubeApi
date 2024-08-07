package com.geeks.youtubeapi.data.repository

import androidx.lifecycle.LiveData
import com.geeks.youtubeapi.data.api_services.YouTubeApiService
import com.geeks.youtubeapi.data.base.BaseRepository
import com.geeks.youtubeapi.data.model.BaseResponse
import com.geeks.youtubeapi.data.model.Item
import com.geeks.youtubeapi.utils.Constants
import com.geeks.youtubeapi.utils.Resource
import retrofit2.Response
import com.geeks.youtubeapi.BuildConfig

const val ARG_ERROR_MESSAGE = "Unknown Error"

class YouTubeRepository(
    private val api: YouTubeApiService
) : BaseRepository() {

    fun getPlayLists(): LiveData<Resource<List<Item>>> = sendRequest {
        val response: Response<BaseResponse> = api.fetchPlaylists(
            channelId = Constants.CHANNEL_ID,
            part = Constants.PART,
            maxResults = Constants.MAX_RESULTS,
            apiKey = BuildConfig.API_KEY
        )
        if (response.isSuccessful) {
            Response.success(response.body()?.items ?: emptyList())
        } else {
            Response.error(response.errorBody()!!, response.raw())
        }
    }

    fun getPlaylistItems(playlistId: String) = doRequest {
        api.getPlaylistItems(
            part = Constants.PART,
            playlistId = playlistId,
            apiKey = BuildConfig.API_KEY,
            maxResults = Constants.MAX_RESULTS
        )
    }
}