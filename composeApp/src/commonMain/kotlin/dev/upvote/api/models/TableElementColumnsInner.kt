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
 * @param type 
 * @param text 
 * @param textForSmallScreens 
 * @param style 
 * @param columnGroupId 
 * @param shownByDefault 
 */

@Serializable
data class TableElementColumnsInner (

    @SerialName("type")
    val type: kotlin.String? = null,

    @SerialName("text")
    val text: kotlin.String? = null,

    @SerialName("text_for_small_screens")
    val textForSmallScreens: kotlin.String? = null,

    @SerialName("style")
    val style: kotlin.String? = null,

    @SerialName("column_group_id")
    val columnGroupId: kotlin.String? = null,

    @SerialName("shown_by_default")
    val shownByDefault: kotlin.Boolean? = null

) {


}
