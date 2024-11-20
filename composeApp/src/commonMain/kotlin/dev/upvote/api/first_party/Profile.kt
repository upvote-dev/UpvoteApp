package dev.upvote.api.first_party

import dev.upvote.data.CreatedAt
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

val DefaultProfile = Profile(
    alias = "SampleAlias",
    username = "SampleUser",
    rank = "paladin",
    coins = 3800,
    profileImageUrl = null,
    createdAt = CreatedAt(0U, 0U)
)

@Serializable
data class ProfileOptional(
    var username: String = "", // will be overridden
    var alias: String? = null,
    @SerialName("profile_image_url")
    val profileImageUrl: String? = null,
)
