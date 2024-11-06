package dev.upvote.data.repository.profile

import kotlinx.coroutines.flow.flow

import dev.upvote.api.ProfileApi
import dev.upvote.api.first_party.ProfileOptional

class DefaultProfileContentRepository(
    private val profileApi: ProfileApi
): ProfileContentRepository {
    override fun getProfile() = flow {
        emit(profileApi.getProfile())
    }

    override fun updateProfile(profile: ProfileOptional) = flow {
        emit(profileApi.updateProfile(profile))
    }
}
