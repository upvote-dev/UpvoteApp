package dev.upvote.presentation.profile

import dev.upvote.api.first_party.Profile

data class ProfileContentState(
    val userId: String?,
    val lastErrorStr: String?,
    val profile: Profile?
)
