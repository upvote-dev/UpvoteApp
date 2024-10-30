package dev.upvote.data.repository.auth

import kotlinx.coroutines.flow.flow

import dev.upvote.api.AuthApi
import dev.upvote.api.first_party.Credentials


class DefaultAuthRepository(
    private val authApi: AuthApi
): AuthRepository {
    override fun acquireToken(credentials: Credentials) = flow {
        emit(authApi.postToken(credentials))
    }
}
