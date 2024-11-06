package dev.upvote.api.first_party

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val username: String,
    val reviewee: String,
    @SerialName("reviewee_kind")
    val revieweeKind: String,
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
data class Reviews(
    val reviews: Array<Review> = emptyArray<Review>(),
    @SerialName("aggregate_rating")
    val aggregateRating: UShort = 0U
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as Reviews

        return reviews.contentEquals(other.reviews)
    }

    override fun hashCode(): Int {
        return reviews.contentHashCode()
    }
}

@Serializable
data class NewReview(
    // val text: String? = null,
    val reviewee: String,
    @SerialName("reviewee_kind")
    val revieweeKind: String,
    val vote: Byte,
    val message: String?,
    @SerialName("photo_url")
    val photoUrl: String?,
    @SerialName("video_url")
    val videoUrl: String?
)

@Serializable
data class CreatedAt(
    @SerialName("nanos_since_epoch")
    val nanosSinceEpoch: ULong,
    @SerialName("secs_since_epoch")
    val secsSinceEpoch: ULong
);
