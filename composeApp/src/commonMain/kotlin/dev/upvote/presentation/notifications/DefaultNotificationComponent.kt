package dev.upvote.presentation.notifications

import com.arkivanov.decompose.ComponentContext


class DefaultNotificationComponent(
    componentContext: ComponentContext,
) : NotificationComponent, ComponentContext by componentContext {
    override fun onBackClicked() {
        TODO("Not yet implemented")
    }
}
