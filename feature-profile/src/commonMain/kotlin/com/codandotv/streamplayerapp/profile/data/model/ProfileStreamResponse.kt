package com.codandotv.streamplayerapp.profile.data.model

import kotlinx.serialization.Serializable

@Serializable
@Suppress("ConstructorParameterNaming")
data class ProfileStreamResponse(
    val id: String,
    val name: String,
    val profile_url: String,
)

@Serializable
data class ProfilesResponse(
    val profiles: List<ProfileStreamResponse>
)