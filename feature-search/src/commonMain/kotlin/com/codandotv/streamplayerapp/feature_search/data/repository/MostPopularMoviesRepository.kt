package com.codandotv.streamplayerapp.feature_search.data.repository

import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse
import com.codandotv.streamplayerapp.feature_search.data.datasource.MostPopularMoviesDataSource
import kotlinx.coroutines.flow.Flow

interface MostPopularMoviesRepository {
    suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse>
}

class MostPopularMoviesRepositoryImpl(
    private val dataSource: MostPopularMoviesDataSource
) : MostPopularMoviesRepository {
    override suspend fun getMostPopularMovies(): Flow<ListSearchStreamResponse> =
        dataSource.getMostPopularMovies()
}
