package dev.upvote.presentation.product

data class ProductDetailState(
    var productName: String? = null,
    var imageFrontUrl: String? = null,
    val firstRun: Boolean = true,
    val isLoading: Boolean = false,
    val lastErrorStr: String? = null
)

/*
sealed interface ProductDetailAction {
    data object GoBack: ProductDetailAction
    data object LookupBarcode: ProductDetailAction
    data object ScanBarcode: ProductDetailAction
    data object ShowProduct: ProductDetailAction
}
*/
