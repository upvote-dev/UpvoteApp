package dev.upvote.presentation.profile

import com.arkivanov.decompose.ComponentContext


class DefaultProfileComponent(
    private val componentContext: ComponentContext,
    private val onFinished: () -> Unit,
) : ProfileComponent, ComponentContext by componentContext {
    override fun onBackClicked() {
        onFinished()
    }
}
