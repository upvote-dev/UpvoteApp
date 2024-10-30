package dev.upvote.data.repository.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

import dev.upvote.api.first_party.Credentials
import dev.upvote.api.first_party.Token

enum class AuthRole {
    ADMIN, REVIEWER, LOGGED_OUT
}

interface AuthRepository {
    val currentRole: Flow<AuthRole>
        get() = flowOf(AuthRole.LOGGED_OUT)
    val host: String
        get() = "foo"

    fun acquireToken(credentials: Credentials): Flow<Token>
}

sealed class InitAuthRepository(
    val currentRole: Flow<AuthRole> = flowOf(AuthRole.LOGGED_OUT)
)
