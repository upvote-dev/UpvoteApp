package dev.upvote.data.repository.profile

import kotlinx.coroutines.flow.Flow

import dev.upvote.api.first_party.Profile

interface ProfileContentRepository {
    fun updateProfile(profile: Profile): Flow<Profile?>
    fun getProfile(): Flow<Profile?>
}
