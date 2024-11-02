package dev.upvote.api.first_party

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    var alias: String,
    val username: String,
    val rank: String,
    val coins: Int,
    @SerialName("profile_image_url")
    val profileImageUrl: String?,
    @SerialName("created_at")
    val createdAt: CreatedAt
)

@Serializable
data class ProfileOptional(
    var username: String,
    var alias: String? = null,
    val rank: String? = null,
    val coins: Int? = null,
    @SerialName("profile_image_url")
    val profileImageUrl: String? = null,
)
