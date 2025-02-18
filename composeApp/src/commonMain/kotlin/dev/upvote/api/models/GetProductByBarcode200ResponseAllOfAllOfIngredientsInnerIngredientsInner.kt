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
import kotlinx.serialization.json.JsonElement

/**
 * 
 *
 * @param id 
 * @param ingredients Sub ingredients composing this ingredients. 
 * @param percent 
 * @param percentEstimate 
 * @param percentMax 
 * @param percentMin 
 * @param text 
 * @param vegan 
 * @param vegetarian 
 */

@Serializable
data class GetProductByBarcode200ResponseAllOfAllOfIngredientsInnerIngredientsInner (

    @SerialName("id")
    val id: kotlin.String? = null,

    /* Sub ingredients composing this ingredients.  */
    @SerialName("ingredients")
    val ingredients: JsonElement? = null,

    @SerialName("percent")
    val percent: Double? = null,

    @SerialName("percent_estimate")
    val percentEstimate: Double? = null,

    @SerialName("percent_max")
    val percentMax: Double? = null,

    @SerialName("percent_min")
    val percentMin: kotlin.Float? = null,

    @SerialName("text")
    val text: kotlin.String? = null,

    @SerialName("vegan")
    val vegan: kotlin.String? = null,

    @SerialName("vegetarian")
    val vegetarian: kotlin.String? = null

) {


}

