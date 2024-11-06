package dev.upvote.presentation.review

import dev.upvote.api.ReviewApi

import kotlinx.coroutines.flow.flow

class DefaultReviewRepository(
    private val reviewApi: ReviewApi
): ReviewRepository {
    override fun getReviews(revieweeKind: String) = flow {
        emit(reviewApi.getReviews(revieweeKind))
    }
}
