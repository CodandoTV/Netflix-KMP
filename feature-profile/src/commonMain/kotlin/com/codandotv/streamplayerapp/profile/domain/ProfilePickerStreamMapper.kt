package com.codandotv.streamplayerapp.profile.domain

import com.codandotv.streamplayerapp.profile.data.model.ProfilesResponse

fun ProfilesResponse.toProfiles(): List<ProfileStream> = this.profiles.map { profileResponse ->
    ProfileStream(
        id = profileResponse.id,
        name = profileResponse.name,
        imageUrl = profileResponse.profile_url,
    )
}
