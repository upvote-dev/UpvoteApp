package dev.upvote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import dev.upvote.presentation.bottom_stack.RootBottomScreen

import org.jetbrains.compose.ui.tooling.preview.Preview

import dev.upvote.presentation.bottom_stack.RootBottomComponent

val PLATFORM = getPlatform()

/*val launchBarcodeScanner = {
    barcode = ""
    productName = ""
    imageFrontUrl = ""

    upvote.ScanBarcode(
        onSuccess = { barcodeScanned ->
            if (barcodeScanned != null) {
                barcode = barcodeScanned
            }
        },
        onCanceled = {},
        onFailure = { e -> println("Scan failed: $e") }
    )
}*/

@Composable
fun ProductDetailComponent() {
    Text("Hello from ProductDetailComponent")
}/*
interface RootComponent {
    val childStack: Value<ChildStack<*, Child>>

    sealed class Child {
        class DetailsChild(val component: ProductDetailComponent) : Child()
    }
}*/

@Composable
@Preview
fun App(
    rootBottomComponent: RootBottomComponent,
    upvote: IUpvote?,
    modifier: Modifier
) {
    val globalState by remember { mutableStateOf(GlobalState()) }
    val barcodeScannerAvailable = hasBarcodeScanner && upvote != null

    if (barcodeScannerAvailable) {
        (GlobalState::attemptBarcodeScan)(globalState, upvote)
    } else {
        globalState.lastErrorStr =
            "hasBarcodeScanner [$hasBarcodeScanner] && upvote != null [$upvote]"
    }

    // val childStack: Value<ChildStack<*, Child>>

    if (globalState.showAuth) {
        println("Opening outermost showAuth")
        dev.upvote.presentation.auth.AuthScreen {
            println("Finished AuthScreen")
            globalState.showAuth = false
        }
    } else if (globalState.lastErrorStr.isNullOrEmpty()
        || !globalState.barcode.isNullOrEmpty()
        || !barcodeScannerAvailable
    )
        MaterialTheme {
            /*RootContent(
                component = rootComponent,
                modifier = Modifier.fillMaxSize(),
            )*//*
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                // ProductDetail(upvote)
            }
            */
            Scaffold(
                modifier = modifier,
                /*floatingActionButton = {
                    if (barcodeScannerAvailable) {
                        FloatingActionButton(onClick = actuallyScanBarcode) {
                            Icon(
                                painterResource(Res.drawable.barcode_scanner_24px),
                                contentDescription = "Scan barcode",
                                modifier = Modifier
                                    .size(FloatingActionButtonDefaults.LargeIconSize),
                                /*contentWindowInsets = WindowInsets.navigationBars.only(
                                    WindowInsetsSides.Horizontal)*/
                            )
                        }
                    }
                }*/
            ) { paddingFromPrent ->
                Column(
                    Modifier.padding(paddingFromPrent).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RootBottomScreen(
                        globalState = globalState,
                        upvote = upvote,
                        component = rootBottomComponent,
                        modifier = modifier
                    )
                }
            }
        }
}
