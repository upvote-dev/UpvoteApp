package dev.upvote.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedAt(
    @SerialName("nanos_since_epoch")
    val nanosSinceEpoch: ULong,
    @SerialName("secs_since_epoch")
    val secsSinceEpoch: ULong
)
