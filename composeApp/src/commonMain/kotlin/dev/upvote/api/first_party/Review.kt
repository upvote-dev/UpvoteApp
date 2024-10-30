package dev.upvote.api.first_party

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val username: String,
    val reviewee: String,
    val vote: Byte,
    val message: String?,
    @SerialName("photo_url")
    val photoUrl: String?,
    @SerialName("video_url")
    val videoUrl: String?,
    @SerialName("created_at")
    val createdAt: CreatedAt
)

@Serializable
data class NewReview(
    // val text: String? = null,
    val reviewee: String,
    val vote: Byte,
    val message: String?,
    @SerialName("photo_url")
    val photoUrl: String?,
    @SerialName("video_url")
    val videoUrl: String?
)

@Serializable
data class CreatedAt(
    val nanos_since_epoch: ULong,
    val secs_since_epoch: ULong
);
