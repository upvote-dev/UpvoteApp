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

import dev.upvote.api.models.GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAdjustmentsOriginsOfIngredientsAggregatedOriginsInner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonElement

/**
 * 
 *
 * @param aggregatedOrigins 
 * @param epiScore 
 * @param epiValue 
 * @param originsFromOriginsField 
 * @param transportationScores 
 * @param transportationValues 
 * @param propertyValues 
 * @param warning 
 */

@Serializable
data class GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAdjustmentsOriginsOfIngredients (

    @SerialName("aggregated_origins")
    val aggregatedOrigins: kotlin.collections.List<GetProductByBarcode200ResponseAllOfAllOfEcoscoreDataAdjustmentsOriginsOfIngredientsAggregatedOriginsInner>? = null,

    @SerialName("epi_score")
    val epiScore: kotlin.Int? = null,

    @SerialName("epi_value")
    val epiValue: kotlin.Int? = null,

    @SerialName("origins_from_origins_field")
    val originsFromOriginsField: kotlin.collections.List<kotlin.String>? = null,

    @SerialName("transportation_scores")
    val transportationScores: JsonElement? = null,

    @SerialName("transportation_values")
    val transportationValues: JsonElement? = null,

    @SerialName("values")
    val propertyValues: JsonElement? = null,

    @SerialName("warning")
    val warning: kotlin.String? = null

) {


}

