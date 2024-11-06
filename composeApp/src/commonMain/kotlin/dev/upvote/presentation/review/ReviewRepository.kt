package dev.upvote.presentation.review

import kotlinx.coroutines.flow.Flow

import dev.upvote.api.first_party.Reviews

interface ReviewRepository {
    fun getReviews(revieweeKind: String): Flow<Reviews>
}
