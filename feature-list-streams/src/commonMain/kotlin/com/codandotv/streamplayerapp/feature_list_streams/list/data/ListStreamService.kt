package com.codandotv.streamplayerapp.feature_list_streams.list.data

import ListStreamResponse
import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.feature_list_streams.list.data.model.GenresResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.parameter
import io.ktor.client.request.url

interface ListStreamService {
    suspend fun getMovies(genres: String): NetworkResponse<ListStreamResponse>
    suspend fun getPaginatedMovies(genres: String, page: Int): NetworkResponse<ListStreamResponse>
    suspend fun getGenres(): NetworkResponse<GenresResponse>
    suspend fun getTopRatedMovies(
        sortBy: String = "vote_average.desc",
        page: Int = 1
    ): NetworkResponse<ListStreamResponse>
}

class ListStreamServiceImpl(
    private val client: HttpClient
) : ListStreamService {

    override suspend fun getMovies(genres: String): NetworkResponse<ListStreamResponse> {
        return client.safeRequest<ListStreamResponse> {
            url("discover/movie")
            parameter("with_genres", genres)
        }
    }

    override suspend fun getPaginatedMovies(
        genres: String,
        page: Int
    ): NetworkResponse<ListStreamResponse> {
        return client.safeRequest {
            url("discover/movie")
            parameter("with_genres", genres)
            parameter("page", page)
        }
    }

    override suspend fun getGenres(): NetworkResponse<GenresResponse> {
        return client.safeRequest {
            url("genre/movie/list")
        }
    }

    override suspend fun getTopRatedMovies(
        sortBy: String,
        page: Int
    ): NetworkResponse<ListStreamResponse> {
        return client.safeRequest {
            url("discover/movie")
            parameter("sort_by", sortBy)
            parameter("page", page)
        }
    }
}
