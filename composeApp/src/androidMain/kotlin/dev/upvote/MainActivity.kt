package dev.upvote

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.arkivanov.decompose.defaultComponentContext

import dev.upvote.presentation.bottom_stack.DefaultRootBottomComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContext.set(this)

        // val rootComponentFactory: RootComponent.Factory = DependencyInjection.rootComponentFactory

        // Always create the root component outside Compose on the main thread
        val rootBottomComponent = DefaultRootBottomComponent(
            componentContext = defaultComponentContext()
        )

        setContent {
            val scope = rememberCoroutineScope()
            App(
                rootBottomComponent = rootBottomComponent,
                upvote = getUpvote(),
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    /*val rootComponentFactory: RootComponent.Factory = DependencyInjection.rootComponentFactory

    // Always create the root component outside Compose on the main thread
    val rootComponent = rootComponentFactory(defaultComponentContext())
    App(rootComponent, getUpvote())*/
}
