package dev.upvote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess

import dev.upvote.BASE_URL
import dev.upvote.SUCCESS_HTTP_CODES
import dev.upvote.api.first_party.Profile
import dev.upvote.api.first_party.ProfileOptional
import dev.upvote.globalGlobalState

class ProfileApi(private val httpClient: HttpClient) {
    suspend fun getProfile(): Profile? {
        val response: HttpResponse = httpClient.get("$BASE_URL/api/v0/profile") {
            headers {
                append(HttpHeaders.Accept, ContentType.Application.Json.toString())
            }
        }
        println("getProfile status = ${response.status}")
        return when (response.status.value) {
            in SUCCESS_HTTP_CODES -> response.body()
            404 -> null
            else -> throw HttpResponseException(response)
        }
    }

    suspend fun updateProfile(profile: ProfileOptional): Profile {
        if (globalGlobalState.value.token == null) {
            throw AuthError("not logged in")
        }
        profile.username = globalGlobalState.value.token?.getUsername().toString()
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
