package dev.upvote.presentation.auth.signinup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

import kotlin.coroutines.cancellation.CancellationException

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.bodyAsText
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

import dev.upvote.presentation.auth.AuthState
import dev.upvote.api.HttpResponseException
import dev.upvote.api.first_party.Credentials
import dev.upvote.api.first_party.Token
import dev.upvote.data.repository.auth.AuthRepository
import dev.upvote.data.repository.auth.DefaultAuthRepository
import dev.upvote.api.AuthApi
import dev.upvote.bearerTokenStorage
import dev.upvote.globalGlobalState
import dev.upvote.globalMutableStateFlow
import dev.upvote.httpClient
import dev.upvote.invalidateBearerTokens
import dev.upvote.settings
import io.ktor.client.plugins.auth.authProvider
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.auth.providers.BearerTokens

class SignInUpViewModel(
    private val authRepository: AuthRepository = DefaultAuthRepository(
        AuthApi(HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(Json {
                    encodeDefaults = true
                    isLenient = true
                    allowSpecialFloatingPointValues = true
                    allowStructuredMapKeys = true
                    prettyPrint = false
                    useArrayPolymorphism = false
                    ignoreUnknownKeys = true
                })
            }
        })
    )
) : ViewModel() {
    private val _uiState =
        MutableStateFlow(AuthState(lastErrorStr = null, token = globalGlobalState.value.token, userId = null))
    val uiState: StateFlow<AuthState> = _uiState.asStateFlow()

    private fun setErrorStr(errorStr: String) {
        _uiState.update {
            it.copy(
                lastErrorStr = errorStr
            )
        }
    }

    fun clearError() {
        _uiState.update {
            it.copy(
                lastErrorStr = null,
                token = null,
                userId = null
            )
        }
    }

    fun setToken(token: Token) {
        _uiState.update {
            it.copy(
                lastErrorStr = null,
                token = token
            )
        }
        globalMutableStateFlow.update {
            it.copy(
                token = token
            )
        }
        httpClient.authProvider<BearerAuthProvider>()?.clearToken()
        httpClient.invalidateBearerTokens()
        bearerTokenStorage.add(BearerTokens(token.accessToken, token.accessToken))
        settings.putString("accessToken", token.accessToken)
    }

    fun acquireToken(credentials: Credentials) {
        viewModelScope.launch {
            try {
                val token = authRepository.acquireToken(credentials).first()
                println("token: $token")
                setToken(token)
            } catch (e: CancellationException) {
                currentCoroutineContext().ensureActive()
            } catch (e: HttpResponseException) {
                setErrorStr("[${e.response.status}] ${e.response.bodyAsText()}")
            } catch (e: SocketTimeoutException) {
                setErrorStr("[SocketTimeoutException] ${e.message}")
            } catch (e: io.ktor.client.call.NoTransformationFoundException) {
                setErrorStr("[NoTransformationFoundException] ${e.message}")
            }
        }
    }

    fun getToken(): Token? = uiState.value.token
}
