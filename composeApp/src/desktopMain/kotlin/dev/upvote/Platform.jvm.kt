package dev.upvote

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual val hasBarcodeScanner: Boolean
    get() = false

actual class Upvote: IUpvote {
    override fun ScanBarcode(
        onSuccess: (String?) -> Unit,
        onCanceled: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        onFailure(Exception("TODO " + getPlatform().name))
    }
}

actual fun getUpvote(): IUpvote? {
    return Upvote()
}
