package dev.upvote

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle

import dev.upvote.presentation.bottom_stack.DefaultRootBottomComponent

fun MainViewController() = ComposeUIViewController {
    val rootBottomComponent = remember {
        DefaultRootBottomComponent(
            componentContext = DefaultComponentContext(ApplicationLifecycle())
        )
    }

    App(rootBottomComponent = rootBottomComponent,
        upvote = getUpvote(),
        modifier = Modifier)
}
