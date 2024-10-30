package dev.upvote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect val hasBarcodeScanner: Boolean

/* cannot use `typealias` for js target
typealias BarcodeScanSuccess = (String?) -> Unit
typealias BarcodeScanCanceled = () -> Unit
typealias BarcodeScanFailure = (Exception) -> Unit
*/

interface IUpvote {
    fun ScanBarcode(onSuccess: (String?) -> Unit,
                    onCanceled: () -> Unit,
                    onFailure: (Exception) -> Unit)
}

expect class Upvote: IUpvote

expect fun getUpvote(): IUpvote?
