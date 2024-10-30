package dev.upvote.api

import io.ktor.client.statement.HttpResponse

class HttpResponseException(val response: HttpResponse) : Exception(response.toString())
