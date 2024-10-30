package dev.upvote.presentation.leaderboard

import com.arkivanov.decompose.ComponentContext

class DefaultLeaderboardComponent(
    componentContext: ComponentContext,
    private val onShowWelcome: () -> Unit,

) : LeaderboardComponent, ComponentContext by componentContext {
    override fun onBackClicked() {
        TODO("Not yet implemented")
    }
}