package dev.upvote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess

import dev.upvote.BASE_URL
import dev.upvote.api.first_party.Credentials
import dev.upvote.api.first_party.Token


class AuthApi(private val httpClient: HttpClient) {
    suspend fun postToken(credentials: Credentials): Token {
        println("postToken")
        val response: HttpResponse = httpClient.post("$BASE_URL/api/token") {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
            contentType(ContentType.Application.Json)
            setBody(credentials)
        }
        println("postToken status = ${response.status}")
        if (response.status.isSuccess())
            return response.body()
        throw HttpResponseException(response)
    }
}
