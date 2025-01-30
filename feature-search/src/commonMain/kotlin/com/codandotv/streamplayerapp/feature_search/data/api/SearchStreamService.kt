package com.codandotv.streamplayerapp.feature_search.data.api

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.url

interface SearchStreamService {
    suspend fun getSearch(query: String): NetworkResponse<ListSearchStreamResponse>
}

class SearchStreamServiceImpl(
    private val client: HttpClient
) : SearchStreamService {
    override suspend fun getSearch(query: String): NetworkResponse<ListSearchStreamResponse> =
        client.safeRequest {
            url("search/movie")
            parameter("query", query)
        }
}
