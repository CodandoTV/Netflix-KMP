package com.codandotv.streamplayerapp.feature_search.domain.mapper

import com.codandotv.streamplayerapp.core_shared.Url
import com.codandotv.streamplayerapp.feature_search.data.model.ListSearchStreamResponse.SearchStreamResponse
import com.codandotv.streamplayerapp.feature_search.presentation.widgets.SearchStreamCardModel

fun SearchStreamResponse.toSearchStreamCardModel() = SearchStreamCardModel(
    id = id.toString(),
    title = title,
    url = "${Url.IMAGE_URL_SIZE_200}${posterPath}"
)
