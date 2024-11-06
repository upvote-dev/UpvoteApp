package dev.upvote.presentation.review

import com.arkivanov.decompose.ComponentContext

class DefaultReviewComponent(
    componentContext: ComponentContext,
    private val onShowWelcome: () -> Unit,
): ReviewComponent, ComponentContext by componentContext {
    override fun onBackClicked() {
        TODO("Not yet implemented")
    }
}
