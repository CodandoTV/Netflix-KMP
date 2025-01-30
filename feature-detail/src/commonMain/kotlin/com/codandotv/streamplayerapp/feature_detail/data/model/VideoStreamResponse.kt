package com.codandotv.streamplayerapp.feature_detail.data.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoStreamResponse(
    val id: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val official: Boolean,
    val type: String,
)

@Serializable
data class VideoStreamsResponse(
    val id: Long,
    val results: List<VideoStreamResponse>
)