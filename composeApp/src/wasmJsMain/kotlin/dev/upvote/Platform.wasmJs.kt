package dev.upvote

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()
actual val hasBarcodeScanner: Boolean
    get() = false


actual class Upvote: IUpvote {
    override fun ScanBarcode(
        onSuccess: (String?) -> Unit,
        onCanceled: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        onFailure(Exception("TODO " + WasmPlatform::name))
    }
}

actual fun getUpvote(): IUpvote? {
    return Upvote()
}
