package dev.upvote.presentation.bottom_stack

import org.jetbrains.compose.resources.painterResource

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation

import dev.upvote.presentation.product.ProductScreen
import dev.upvote.presentation.leaderboard.LeaderboardContent
import dev.upvote.GlobalState
import dev.upvote.IUpvote
import dev.upvote.presentation.notifications.NotificationContent
import dev.upvote.presentation.profile.ProfileScreen
import dev.upvote.presentation.review.ReviewScreen
import dev.upvote.resources.Res
import dev.upvote.resources.barcode_scanner_24px
import dev.upvote.resources.emoji_events_24px

data class ScreensBottom(
    val name: String,
    val openScreen: () -> Unit,
    val isSelected: Boolean
)


@Composable
fun RootBottomScreen(
    globalState: GlobalState,
    upvote: IUpvote?,
    component: RootBottomComponent,
    modifier: Modifier = Modifier,
) {
    var selectedItem by remember { mutableIntStateOf(1) }
    val screens by remember {
        mutableStateOf(
            listOf(
                ScreensBottom("Profile", component::openProfile, false),
                ScreensBottom("Product", component::openProductScreen, false),
                ScreensBottom("Reviews", component::openReviews, false),
                ScreensBottom("Notify", component::openNotifications, false),
                ScreensBottom("Leaderboard", component::openLeaderboardScreen, false)
            )
        )
    }

    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(
                    onClick = {
                        (GlobalState::attemptBarcodeScan)(globalState, upvote)
                        (component::openProductScreen)()
                    },
                    shape = CircleShape,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .size(80.dp)
                        .offset(y = 50.dp),
                    containerColor = Color.White
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.barcode_scanner_24px),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(.8F)
                    )
                }
            }
        },
        // isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    screens.forEachIndexed { index, screensBottom ->
                        NavigationBarItem(
                            icon = {
                                when (screensBottom.name) {
                                    "Profile" -> Icon(
                                        Icons.Outlined.Face,
                                        contentDescription = null
                                    )

                                    "Product" -> Icon(
                                        Icons.Outlined.ShoppingCart,
                                        contentDescription = null
                                    )

                                    "Review" -> Icon(
                                        Icons.Outlined.Star,
                                        contentDescription = null
                                    )

                                    "Notifications" -> Icon(
                                        Icons.Default.Call,
                                        contentDescription = null
                                    )

                                    "Leaderboard" -> Icon(
                                        painter = painterResource(Res.drawable.emoji_events_24px),
                                        contentDescription = null
                                    )
                                }
                            },
                            label = {
                                Text(
                                    text = screensBottom.name,
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Light
                                )
                            },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                screensBottom.openScreen()
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Children(
                    stack = component.childStackBottom,
                    modifier = modifier,
                    animation = stackAnimation(fade() + scale()),
                ) {
                    when (val child = it.instance) {
                        is RootBottomComponent.ChildBottom.ProfileChild ->
                            ProfileScreen(
                                component = child.component
                            )

                        is RootBottomComponent.ChildBottom.ProductChild ->
                            ProductScreen(
                                component = child.component,
                                modifier = modifier
                            )

                        is RootBottomComponent.ChildBottom.ReviewChild ->
                            ReviewScreen(
                                component = child.component,
                                modifier = modifier
                            )

                        is RootBottomComponent.ChildBottom.NotificationsChild ->
                            NotificationContent(
                                component = child.component
                            )

                        is RootBottomComponent.ChildBottom.LeaderboardChild ->
                            LeaderboardContent(
                                component = child.component,
                                modifier = modifier
                            )
                    }
                }
            }
        })

    LaunchedEffect(1) {
        //screensBottom.openScreen()
    }
}
