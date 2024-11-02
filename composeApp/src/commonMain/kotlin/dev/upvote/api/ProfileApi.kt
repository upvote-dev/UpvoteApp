package dev.upvote.api

import dev.upvote.BASE_URL
import io.ktor.client.HttpClient

import dev.upvote.api.first_party.Profile
import dev.upvote.api.first_party.ProfileOptional
import dev.upvote.globalGlobalState
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProfileApi(private val httpClient: HttpClient) {
    suspend fun getProfile(): Profile? {
        return null
    }

    suspend fun updateProfile(profile: ProfileOptional): Profile {
        val implicitNulls = Json { explicitNulls = false }
        val j = Json {
            allowSpecialFloatingPointValues = true
            allowStructuredMapKeys = true
            encodeDefaults = false
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
            prettyPrint = false
            useArrayPolymorphism = false
        }

        val response: HttpResponse = httpClient.post("$BASE_URL/api/v0/profile") {
            headers {
                append(HttpHeaders.Accept, ContentType.Application.Json.toString())
            }
            contentType(ContentType.Application.Json)
            setBody(profile)
        }
        println("updateProfile status = ${response.status}")
        if (response.status.isSuccess())
            return response.body()
        throw HttpResponseException(response)
    }
}
