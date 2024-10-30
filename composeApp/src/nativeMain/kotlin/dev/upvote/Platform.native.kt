package dev.upvote

actual val hasBarcodeScanner: Boolean
    get() = true

actual class Upvote: IUpvote {
    override fun ScanBarcode(
        onSuccess: (String?) -> Unit,
        onCanceled: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        println("[kotlin/dev/upvote/Platform.native.kt] ScanBarcode")
        onFailure(Exception("TODO " + getPlatform().name))
    }
}

actual fun getUpvote(): IUpvote? {
    return null
}
