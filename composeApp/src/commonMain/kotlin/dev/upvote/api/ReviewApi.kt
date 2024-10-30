package dev.upvote.api

import dev.upvote.BASE_URL
import dev.upvote.api.first_party.NewReview
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

import dev.upvote.api.first_party.Review

class ReviewApi(private val httpClient: HttpClient) {
    suspend fun addReview(review: NewReview): Review {
        println("addReview")
        println("ContentType.Application.Json.toString(): ${ContentType.Application.Json.toString()}")
        val response: HttpResponse = httpClient.post("$BASE_URL/api/v0/review") {
            headers {
                append(HttpHeaders.Accept, "application/json")
            }
            contentType(ContentType.Application.Json)
            setBody(review)
        }
        println("addReview status = ${response.status}")
        if (response.status.isSuccess())
            return response.body()
        throw HttpResponseException(response)
    }
}
