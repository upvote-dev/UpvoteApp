package dev.upvote.presentation.review

import dev.upvote.api.first_party.Reviews

data class ReviewsState(
    val reviews: Reviews,
    val lastErrorStr: String? = null
)
