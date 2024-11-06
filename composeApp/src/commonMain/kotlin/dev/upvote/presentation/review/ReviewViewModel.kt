package dev.upvote.presentation.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlin.coroutines.cancellation.CancellationException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode

import dev.upvote.api.HttpResponseException
import dev.upvote.api.ReviewApi
import dev.upvote.api.first_party.Reviews
import dev.upvote.httpClient


class ReviewViewModel(
    private val reviewRepository: ReviewRepository = DefaultReviewRepository(
        ReviewApi(httpClient)
    )
) : ViewModel() {
    private val _uiState = MutableStateFlow(ReviewsState(reviews = Reviews()))
    val uiState: StateFlow<ReviewsState> = _uiState.asStateFlow()

    private fun setErrorStr(errorStr: String) {
        _uiState.update {
            it.copy(
                lastErrorStr = errorStr
            )
        }
    }

    private fun setReviews(reviews: Reviews) {
        _uiState.update {
            it.copy(
                reviews = reviews
            )
        }
    }

    fun getReviews(revieweeKind: String) {
        viewModelScope.launch {
            try {
                setReviews(reviewRepository.getReviews(revieweeKind).first())
            } catch (e: CancellationException) {
                currentCoroutineContext().ensureActive()
            } catch (e: HttpResponseException) {
                if (e.response.status == HttpStatusCode.NotFound) {
                    // empty array is default
                    // setErrorStr("Reviews not found")
                } else {
                    setErrorStr("[${e.response.status}] ${e.response.bodyAsText()}")
                }
            } catch (e: SocketTimeoutException) {
                setErrorStr("[SocketTimeoutException] ${e.message}")
            } catch (e: kotlin.NoSuchElementException) {
                setErrorStr("[kotlin.NoSuchElementException] ${e.message}")
            }
        }
    }
}
