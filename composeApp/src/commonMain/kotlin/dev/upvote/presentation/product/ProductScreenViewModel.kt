package dev.upvote.presentation.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import io.ktor.client.HttpClient
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

import dev.upvote.api.HttpResponseException
import dev.upvote.api.OpenFoodFactsApi
import dev.upvote.api.ReviewApi
import dev.upvote.api.first_party.NewReview
import dev.upvote.data.repository.review.DefaultReviewRepository
import dev.upvote.data.repository.review.ReviewRepository
import dev.upvote.httpClient

class ProductDetailViewModel(
    private val openFoodFactsApi: OpenFoodFactsApi = OpenFoodFactsApi(HttpClient {
        /*expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, _ /* request */ ->
                val clientException = exception as? ClientRequestException
                    ?: return@handleResponseExceptionWithRequest
                val exceptionResponse = clientException.response
                if (exceptionResponse.status == HttpStatusCode.NotFound) {
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    productDetailStateMutableFlow.update { it.copy(lastErrorStr = "[404] $exceptionResponseText") }
                } else {
                    productDetailStateMutableFlow.update {
                        it.copy(
                            lastErrorStr = "[${exceptionResponse.status.value}] ${exceptionResponse.bodyAsText()}"
                        )
                    }
                }
            }
        }*/
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
    }),
    private val reviewRepository: ReviewRepository = DefaultReviewRepository(
        ReviewApi(httpClient)
    )
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductDetailState(firstRun = true))
    val uiState: StateFlow<ProductDetailState> = _uiState.asStateFlow()

    private fun setProductNameAndImageFrontUrl(
        productName: String?,
        imageFrontUrl: String?
    ) {
        _uiState.update {
            it.copy(
                productName = productName,
                imageFrontUrl = imageFrontUrl
            )
        }
    }

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
                productName = null,
                imageFrontUrl = null
            )
        }
    }

    fun getProductById(barcode: String) {
        viewModelScope.launch {
            try {
                val product = openFoodFactsApi.productById(barcode).product
                /*
                val product = coroutineContext.async {
                    openFoodFactsApi.productById(barcode)
                }.await().product*/
                if (product != null) {
                    setProductNameAndImageFrontUrl(
                        productName = product.productName,
                        imageFrontUrl = product.imageFrontUrl
                    )
                }
            } catch (e: CancellationException) {
                currentCoroutineContext().ensureActive()
            } catch (e: HttpResponseException) {
                if (e.response.status == HttpStatusCode.NotFound) {
                    setErrorStr("Product not found")
                } else {
                    setErrorStr("[${e.response.status}] ${e.response.bodyAsText()}")
                }
            } catch (e: SocketTimeoutException) {
                setErrorStr("[SocketTimeoutException] ${e.message}")
            }
        }
    }

    fun postReview(review: NewReview) {
        viewModelScope.launch {
            try {
                val reviewCreated = reviewRepository.addReview(review).first()
                println("Added: $reviewCreated")
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
}
