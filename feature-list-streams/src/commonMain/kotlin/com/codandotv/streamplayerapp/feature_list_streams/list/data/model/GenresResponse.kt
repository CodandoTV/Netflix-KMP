package com.codandotv.streamplayerapp.feature_list_streams.list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    val id: Long,
    val name: String
)

@Serializable
data class GenresResponse(
    val genres: List<GenreResponse>
)