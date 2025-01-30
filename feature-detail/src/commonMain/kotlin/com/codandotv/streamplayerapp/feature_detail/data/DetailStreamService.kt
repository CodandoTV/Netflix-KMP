package com.codandotv.streamplayerapp.feature_detail.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature_detail.data.model.DetailStreamResponse
import com.codandotv.streamplayerapp.feature_detail.data.model.VideoStreamsResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.url

interface DetailStreamService {
    suspend fun getMovie(movieId: String): NetworkResponse<DetailStreamResponse>
    suspend fun getVideoStreams(movieId: String): NetworkResponse<VideoStreamsResponse>
}

class DetailStreamServiceImpl(
    private val client: HttpClient
) : DetailStreamService {

    override suspend fun getMovie(movieId: String): NetworkResponse<DetailStreamResponse> =
        client.safeRequest {
            url("movie/$movieId")
        }

    override suspend fun getVideoStreams(movieId: String): NetworkResponse<VideoStreamsResponse> =
        client.safeRequest {
            url("movie/$movieId/videos")
        }
}
