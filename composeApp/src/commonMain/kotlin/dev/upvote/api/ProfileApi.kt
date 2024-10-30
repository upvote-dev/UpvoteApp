package dev.upvote.api

import io.ktor.client.HttpClient

import dev.upvote.api.first_party.Profile

class ProfileApi(private val httpClient: HttpClient) {
    suspend fun getProfile(): Profile? {
        return null
    }

    suspend fun updateProfile(profile: Profile): Profile {
        TODO()
    }
}
