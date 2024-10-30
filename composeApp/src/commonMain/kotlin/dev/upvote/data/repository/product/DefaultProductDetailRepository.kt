package dev.upvote.data.repository.product

import kotlinx.coroutines.flow.flow

import dev.upvote.api.OpenFoodFactsApi

class DefaultProductDetailRepository(
    private val openFoodFactsApi: OpenFoodFactsApi
): ProductDetailRepository {
    override fun getProductById(barcode: String) = flow {
        emit(openFoodFactsApi.productById(barcode).product)
    }
}
