package dev.upvote.api.first_party

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    @SerialName("grant_type")
    val grantType: String = "password",
    val username: String? = null,
    val password: String? = null,
    @SerialName("client_id")
    val clientId: String? = null,
    @SerialName("client_secret")
    val clientSecret: String? = null,
)
