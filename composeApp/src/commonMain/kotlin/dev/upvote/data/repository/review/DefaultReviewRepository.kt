package dev.upvote.data.repository.review

import kotlinx.coroutines.flow.flow

import dev.upvote.api.ReviewApi
import dev.upvote.api.first_party.NewReview

class DefaultReviewRepository(
    private val reviewApi: ReviewApi
): ReviewRepository {
    override fun addReview(review: NewReview) = flow {
        emit(reviewApi.addReview(review))
    }
}
