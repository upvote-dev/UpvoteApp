package dev.upvote.presentation.bottom_stack

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

import dev.upvote.presentation.product.ProductComponent
import dev.upvote.presentation.leaderboard.LeaderboardComponent
import dev.upvote.presentation.notifications.NotificationComponent
import dev.upvote.presentation.profile.ProfileComponent
import dev.upvote.presentation.review.ReviewComponent


interface RootBottomComponent {
    val childStackBottom: Value<ChildStack<*, ChildBottom>>
    fun openProfile()
    fun openProductScreen()
    fun openReviews()
    fun openNotifications()
    fun openLeaderboardScreen()

    sealed class ChildBottom {
        class ProfileChild(val component: ProfileComponent) : ChildBottom()
        class ProductChild(val component: ProductComponent) : ChildBottom()
        class ReviewChild(val component: ReviewComponent) : ChildBottom()
        class NotificationsChild(val component: NotificationComponent) : ChildBottom()
        class LeaderboardChild(val component: LeaderboardComponent) : ChildBottom()
    }
}
