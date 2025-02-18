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

import dev.upvote.api.models.GetProductByBarcode200ResponseAllOfAllOfImages1
import dev.upvote.api.models.GetProductByBarcode200ResponseAllOfAllOfImagesFront

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * This contains properties for all images contained on the product. 
 *
 * @param _1 
 * @param front 
 */

@Serializable
data class GetProductByBarcode200ResponseAllOfAllOfImages (

    @SerialName("1")
    val _1: GetProductByBarcode200ResponseAllOfAllOfImages1? = null,

    @SerialName("front")
    val front: GetProductByBarcode200ResponseAllOfAllOfImagesFront? = null

) {


}

