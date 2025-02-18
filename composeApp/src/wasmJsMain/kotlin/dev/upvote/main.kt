package dev.upvote

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeViewport

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop

import dev.upvote.presentation.bottom_stack.DefaultRootBottomComponent

import kotlinx.browser.document

import org.w3c.dom.Document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()

    val rootBottomComponent = DefaultRootBottomComponent(
        componentContext = DefaultComponentContext(lifecycle),
    )
    lifecycle.attachToDocument()

    ComposeViewport(document.body!!) {
        App(rootBottomComponent = rootBottomComponent,
            upvote = getUpvote(),
            modifier = Modifier)
    }
}

private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (visibilityState(document) == "visible") {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()

    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

// Workaround for Document#visibilityState not available in Wasm
@JsFun("(document) => document.visibilityState")
private external fun visibilityState(document: Document): String
