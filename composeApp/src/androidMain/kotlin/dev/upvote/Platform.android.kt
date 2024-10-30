package dev.upvote

import java.lang.ref.WeakReference

import android.content.Context
import android.os.Build

import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
actual val hasBarcodeScanner: Boolean
    get() = true

object AppContext {
    private var value: WeakReference<Context?>? = null

    fun set(context: Context) {
        value = WeakReference(context)
    }

    internal fun get(): Context? {
        return value?.get()
    }
}

actual class Upvote(
    private val context: Context,
) : IUpvote {
    private var barcodeScanner: GmsBarcodeScanner

    init {
        val barcodeOptions = GmsBarcodeScannerOptions
            .Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .enableAutoZoom()
            .build()
        this.barcodeScanner = GmsBarcodeScanning.getClient(context, barcodeOptions)
    }

    override fun ScanBarcode(
        onSuccess: (String?) -> Unit,
        onCanceled: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        barcodeScanner.startScan()
            .addOnSuccessListener { barcode ->
                println("Display Value: ${barcode.displayValue}")
                println("Raw Value:     ${barcode.rawValue}")
                println("Format:        ${barcode.format}")
                println("Value Type:    ${barcode.valueType}")
                onSuccess(barcode.rawValue)
            }
            .addOnCanceledListener(onCanceled)
            .addOnFailureListener(onFailure)
    }
}

actual fun getUpvote(): IUpvote? {
    return when (val maybeContext: Context? = AppContext.get()) {
        is Context -> Upvote(maybeContext)
        else -> {
            println("[getUpvote] context is null")
            null
        }
    }
}
