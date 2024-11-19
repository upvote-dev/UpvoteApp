package dev.upvote.data.repository.profile

import kotlinx.coroutines.flow.Flow

import dev.upvote.api.first_party.Profile
import dev.upvote.api.first_party.ProfileOptional

interface ProfileContentRepository {
    fun updateProfile(profile: ProfileOptional): Flow<Profile>
    fun getProfile(): Flow<Profile?>
}
