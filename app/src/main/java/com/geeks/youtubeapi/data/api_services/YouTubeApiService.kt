package com.geeks.youtubeapi.data.api_services

import com.geeks.youtubeapi.data.model.BaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("playlists")
    suspend fun fetchPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int
    ): Response<BaseResponse>

    @GET("playlistItems")
    fun getPlaylistItems(
        @Query("part") part: String,
        @Query("key") apiKey: String,
        @Query("maxResults") maxResults: Int,
        @Query("playlistId") playlistId: String,
        @Query("pageToken") pageToken: String? = null
    ): BaseResponse

}