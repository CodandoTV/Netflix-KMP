package com.codandotv.streamplayerapp.feature_detail.data.model

import kotlinx.serialization.Serializable

@Serializable
@Suppress("ConstructorParameterNaming")
data class DetailStreamResponse(
    val id : Long,
    val title : String,
    val overview : String,
    val tagline : String,
    val backdrop_path : String,
    val release_date : String
)