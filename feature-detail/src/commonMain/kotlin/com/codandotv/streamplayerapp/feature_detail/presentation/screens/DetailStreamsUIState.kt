package com.codandotv.streamplayerapp.feature_detail.presentation.screens

import com.codandotv.streamplayerapp.feature_detail.domain.DetailStream

sealed class DetailStreamsUIState {
    data class DetailStreamsLoadedUIState(
        val detailStream: DetailStream,
        val videoId: String?,
    ) : DetailStreamsUIState()

    object LoadingStreamUIState : DetailStreamsUIState()
}
