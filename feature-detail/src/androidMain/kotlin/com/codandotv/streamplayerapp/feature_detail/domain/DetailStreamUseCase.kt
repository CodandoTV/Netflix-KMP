package com.codandotv.streamplayerapp.feature_detail.domain

import com.codandotv.streamplayerapp.feature_detail.data.DetailStreamRepository
import kotlinx.coroutines.flow.Flow

interface DetailStreamUseCase {
    suspend fun getMovie(): Flow<DetailStream>

    suspend fun toggleItemInFavorites(movie: DetailStream)
}

class DetailStreamUseCaseImpl(
    private val detailStreamRepository: DetailStreamRepository
) : DetailStreamUseCase {

    override suspend fun getMovie(): Flow<DetailStream> =
        detailStreamRepository.getMovie()

    override suspend fun toggleItemInFavorites(movie: DetailStream) {
        if (detailStreamRepository.isFavorite(movie.id)) {
            detailStreamRepository.deleteFromMyList(movie.id)
        } else {
            detailStreamRepository.insertToMyList(movie)
        }
    }
}