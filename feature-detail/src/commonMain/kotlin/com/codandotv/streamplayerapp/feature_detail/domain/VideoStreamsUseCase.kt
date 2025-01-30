package com.codandotv.streamplayerapp.feature_detail.domain

import com.codandotv.streamplayerapp.feature_detail.data.DetailStreamRepository
import kotlinx.coroutines.flow.Flow

interface VideoStreamsUseCase {
    suspend fun getVideoStreams(): Flow<List<VideoStream>>
}

class VideoStreamsUseCaseImpl(
    private val detailStreamRepository: DetailStreamRepository
) : VideoStreamsUseCase {
    override suspend fun getVideoStreams(): Flow<List<VideoStream>> {
        return detailStreamRepository.getVideoStreams()
    }
}