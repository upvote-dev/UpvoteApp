package dev.upvote.data.repository.review

import dev.upvote.api.first_party.NewReview
import dev.upvote.api.first_party.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun addReview(review: NewReview): Flow<Review?>
}
