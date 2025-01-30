package com.codandotv.streamplayerapp.profile.data

import com.codandotv.streamplayerapp.core_networking.handleError.NetworkResponse
import com.codandotv.streamplayerapp.core_networking.handleError.safeRequest
import com.codandotv.streamplayerapp.profile.data.model.ProfilesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.url

interface ProfilePickerStreamService {
    suspend fun getProfiles(): NetworkResponse<ProfilesResponse>
}

class ProfilePickerStreamServiceImpl(
    private val client: HttpClient
) : ProfilePickerStreamService {
    override suspend fun getProfiles(): NetworkResponse<ProfilesResponse> =
        client.safeRequest {
            url("profiles")
        }
}
