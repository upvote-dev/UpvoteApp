package dev.upvote

import io.ktor.client.HttpClient
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.authProviders
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json

fun HttpClient.invalidateBearerTokens() {
    authProviders
        .filterIsInstance<BearerAuthProvider>()
        .singleOrNull()?.clearToken()
}

val bearerTokenStorage = mutableListOf<BearerTokens>()

val httpClient: HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(Json {
            allowSpecialFloatingPointValues = true
            allowStructuredMapKeys = true
            encodeDefaults = false
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = false
            useArrayPolymorphism = false
        })
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }
    install(Auth) {
        bearer {
            loadTokens {
                if (bearerTokenStorage.isNotEmpty()) {
                    bearerTokenStorage.last()
                } else {
                    null
                }
            }
        }
    }
}

/*val VALID_HTTP_CODES = setOf(
    100, 101, 102, 200, 201, 202, 203, 204, 205, 206, 300, 301, 302, 303, 304, 305, 307, 308, 400,
    401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 421,
    422, 423, 424, 426, 428, 429, 431, 451, 500, 501, 502, 503, 504, 505, 506, 507, 508, 510, 511,
)*/

val SUCCESS_HTTP_CODES = setOf(200, 201, 202, 203)
