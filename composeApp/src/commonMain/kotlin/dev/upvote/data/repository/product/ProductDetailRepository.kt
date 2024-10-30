package dev.upvote.data.repository.product

import kotlinx.coroutines.flow.Flow

import dev.upvote.api.models.PatchApiV3ProductBarcode200ResponseAllOfProduct

interface ProductDetailRepository {
    fun getProductById(barcode: String): Flow<PatchApiV3ProductBarcode200ResponseAllOfProduct?>
}
