/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package dev.upvote.api.models

import dev.upvote.api.models.GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAgribalyse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 
 *
 * @param grade 
 * @param score 
 * @param agribalyse 
 */

@Serializable
data class GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataPreviousData (

    @SerialName("grade")
    val grade: kotlin.String? = null,

    @SerialName("score")
    val score: kotlin.Int? = null,

    @SerialName("agribalyse")
    val agribalyse: GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAgribalyse? = null

) {


}

