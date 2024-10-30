package dev.upvote

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

import dev.upvote.presentation.bottom_stack.DefaultRootBottomComponent

fun main() {
    val lifecycle = LifecycleRegistry()

    val rootBottomComponent = runOnUiThread {
        DefaultRootBottomComponent(
            componentContext = DefaultComponentContext(lifecycle),
        )
    }
    application {
        val windowState = rememberWindowState()

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = "Upvote",
        ) {
            LifecycleController(
                lifecycleRegistry = lifecycle,
                windowState = windowState,
                windowInfo = LocalWindowInfo.current,
            )

            App(rootBottomComponent = rootBottomComponent,
                upvote = getUpvote(),
                modifier = Modifier)
        }
    }
}
