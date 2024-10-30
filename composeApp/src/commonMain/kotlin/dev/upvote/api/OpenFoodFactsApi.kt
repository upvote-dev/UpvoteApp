package dev.upvote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

import dev.upvote.api.models.PatchApiV3ProductBarcode200Response

class OpenFoodFactsApi(private val client: HttpClient) {
    private val base = "https://world.openfoodfacts.org/api/v3"

    suspend fun productById(id: String): PatchApiV3ProductBarcode200Response {
        val response: HttpResponse = client.get("$base/product/$id.json")
        println("status = ${response.status}")
        if (response.status.isSuccess())
            return response.body()
        throw HttpResponseException(response)
    }
}
