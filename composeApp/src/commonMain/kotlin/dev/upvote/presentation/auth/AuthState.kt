package dev.upvote.presentation.auth

import dev.upvote.api.first_party.Token

data class AuthState(
    val userId: String?,
    val token: Token?,
    val lastErrorStr: String? = null
) {
    fun isLoggedIn() = token != null
}
