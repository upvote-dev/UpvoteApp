package dev.upvote

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

import dev.upvote.api.first_party.Token

const val BASE_URL = "https://v0.upvote.dev"

data class GlobalState(
    var barcode: String? = null,
    // var product: PatchApiV3ProductBarcode200ResponseAllOfProduct? = null,
    var lastErrorStr: String? = null,
    var showAuth: Boolean = false,
    val token: Token? = null
) {
    fun attemptBarcodeScan(upvote: IUpvote?) {
        if (upvote == null) {
            globalMutableStateFlow.update { it.copy(lastErrorStr = "TODO: Barcode scanning for ${getPlatform().name}") }
        } else {
            upvote.ScanBarcode(
                onSuccess = { barcodeScanned ->
                    if (barcodeScanned != null) {
                        globalMutableStateFlow.update { it.copy(barcode = barcodeScanned) }
                    }
                },
                onCanceled = {
                    // globalMutableStateFlow.update { it.copy(lastErrorStr = "Scan cancelled") }
                    println("Scan cancelled")
                },
                onFailure = { e ->
                    globalMutableStateFlow.update { it.copy(lastErrorStr = e.toString()) }
                }
            )
        }
    }
}

val globalMutableStateFlow = MutableStateFlow(
    GlobalState()
)
val globalGlobalState: StateFlow<GlobalState> = globalMutableStateFlow
