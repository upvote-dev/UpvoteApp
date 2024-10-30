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


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 
 *
 * @param enColonPalmOil 
 * @param enColonVeganStatusUnknown 
 * @param enColonVegetarianStatusUnknown 
 */

@Serializable
data class GetProductByBarcode200ResponseAllOfAllOfIngredientsAnalysis (

    @SerialName("en:palm-oil")
    val enColonPalmOil: kotlin.collections.List<kotlin.String>? = null,

    @SerialName("en:vegan-status-unknown")
    val enColonVeganStatusUnknown: kotlin.collections.List<kotlin.String>? = null,

    @SerialName("en:vegetarian-status-unknown")
    val enColonVegetarianStatusUnknown: kotlin.collections.List<kotlin.String>? = null

) {


}
