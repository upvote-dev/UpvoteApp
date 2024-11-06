package dev.upvote.presentation.bottom_stack

import kotlinx.serialization.Serializable

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.Lifecycle

import dev.upvote.presentation.notifications.DefaultNotificationComponent
import dev.upvote.presentation.notifications.NotificationComponent
import dev.upvote.presentation.leaderboard.LeaderboardComponent
import dev.upvote.presentation.leaderboard.DefaultLeaderboardComponent
import dev.upvote.presentation.product.ProductComponent
import dev.upvote.presentation.product.DefaultProductComponent
import dev.upvote.presentation.profile.DefaultProfileComponent
import dev.upvote.presentation.profile.ProfileComponent
import dev.upvote.presentation.review.DefaultReviewComponent
import dev.upvote.presentation.review.ReviewComponent


class DefaultRootBottomComponent(
    componentContext: ComponentContext
) : RootBottomComponent, ComponentContext by componentContext {
    private val navigationBottomStackNavigation = StackNavigation<ConfigBottom>()

    private val _childStackBottom =
        childStack(
            source = navigationBottomStackNavigation,
            serializer = ConfigBottom.serializer(),
            initialConfiguration = ConfigBottom.Product,
            handleBackButton = true,
            childFactory = ::createChildBottom,
            key = "authStack"
        )

    override val childStackBottom: Value<ChildStack<*, RootBottomComponent.ChildBottom>> =
        _childStackBottom


    private fun createChildBottom(
        config: ConfigBottom,
        componentContext: ComponentContext
    ): RootBottomComponent.ChildBottom =
        when (config) {
            is ConfigBottom.Profile -> RootBottomComponent.ChildBottom.ProfileChild(
                profileComponent(componentContext)
            )

            is ConfigBottom.Product -> RootBottomComponent.ChildBottom.ProductChild(
                productComponent(componentContext)
            )

            is ConfigBottom.Review -> RootBottomComponent.ChildBottom.ReviewChild(
                reviewComponent(componentContext)
            )

            is ConfigBottom.Leaderboard -> RootBottomComponent.ChildBottom.LeaderboardChild(
                leaderboardComponent(componentContext)
            )

            is ConfigBottom.Notification -> RootBottomComponent.ChildBottom.NotificationsChild(
                notificationComponent(componentContext)
            )
        }


    private fun profileComponent(componentContext: ComponentContext): ProfileComponent =
        DefaultProfileComponent(
            componentContext = componentContext,
            onFinished = {}
        )

    private fun productComponent(componentContext: ComponentContext): ProductComponent =
        DefaultProductComponent(
            componentContext = componentContext,
            onShowWelcome = {}
        )

    private fun reviewComponent(componentContext: ComponentContext): ReviewComponent =
        DefaultReviewComponent(
            componentContext = componentContext,
            onShowWelcome = {}
        )

    private fun leaderboardComponent(componentContext: ComponentContext): LeaderboardComponent =
        DefaultLeaderboardComponent(
            componentContext = componentContext,
            onShowWelcome = {}
        )

    private fun notificationComponent(componentContext: ComponentContext): NotificationComponent =
        DefaultNotificationComponent(
            componentContext = componentContext
        )

    override fun openProfile() {
        navigationBottomStackNavigation.bringToFront(ConfigBottom.Profile)
    }

    override fun openProductScreen() {
        navigationBottomStackNavigation.bringToFront(ConfigBottom.Product)
    }

    override fun openReviews() {
        navigationBottomStackNavigation.bringToFront(ConfigBottom.Review)
    }

    override fun openLeaderboardScreen() {
        navigationBottomStackNavigation.bringToFront(ConfigBottom.Leaderboard)
    }

    override fun openNotifications() {
        navigationBottomStackNavigation.bringToFront(ConfigBottom.Notification)
    }

    @Serializable
    private sealed class ConfigBottom {
        @Serializable
        data object Profile : ConfigBottom()

        @Serializable
        data object Product : ConfigBottom()

        @Serializable
        data object Review : ConfigBottom()

        @Serializable
        data object Leaderboard : ConfigBottom()

        @Serializable
        data object Notification : ConfigBottom()
    }

    init {
        lifecycle.subscribe(object : Lifecycle.Callbacks {
            override fun onResume() {
                when (childStackBottom.active.configuration) {
                    is ConfigBottom.Leaderboard -> {
                        super.onResume()
                    }

                }
            }
        })
    }
}
