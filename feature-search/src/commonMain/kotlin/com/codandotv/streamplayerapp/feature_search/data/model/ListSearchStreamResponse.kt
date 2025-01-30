package com.codandotv.streamplayerapp.feature_search.data.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class ListSearchStreamResponse(
    @Json(name = "results")
    val results: List<SearchStreamResponse>
) {
    @Serializable
    data class SearchStreamResponse(
        @Json(name = "id")
        val id: Int,
        @Json(name = "title")
        val title: String,
        @Json(name="overview")
        val overview: String,
        @Json(name = "poster_path")
        val posterPath: String? = null
    )
}
