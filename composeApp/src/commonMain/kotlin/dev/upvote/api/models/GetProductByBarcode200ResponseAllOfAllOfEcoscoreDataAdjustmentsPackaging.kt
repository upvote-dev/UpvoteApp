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

import dev.upvote.api.models.GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAdjustmentsPackagingPackagingsInner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 
 *
 * @param nonRecyclableAndNonBiodegradableMaterials 
 * @param packagings 
 * @param score 
 * @param `value` 
 * @param warning 
 */

@Serializable
data class GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAdjustmentsPackaging (

    @SerialName("non_recyclable_and_non_biodegradable_materials")
    val nonRecyclableAndNonBiodegradableMaterials: kotlin.Int? = null,

    @SerialName("packagings")
    val packagings: kotlin.collections.List<GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAdjustmentsPackagingPackagingsInner>? = null,

    @SerialName("score")
    val score: kotlin.Int? = null,

    @SerialName("value")
    val `value`: kotlin.Int? = null,

    @SerialName("warning")
    val warning: kotlin.String? = null

) {


}

