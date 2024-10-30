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
 * The title of a panel.
 *
 * @param name A short name of this panel, not including any actual values
 * @param title 
 * @param type Used to indicate how the value of this item is measured, such as \"grade\" for Nutri-Score and Eco-Score or \"percentage\" for Salt
 * @param grade The value for this panel where it corresponds to a A to E grade such as the Nutri-Score of the Eco-Score.
 * @param `value` The numeric value of the panel, where the type is \"percentage\"
 * @param iconUrl 
 * @param iconColorFromEvaluation 
 * @param iconSize If set to \"small\", the icon should be displayed at a small size. 
 */

@Serializable
data class TitleElement (

    /* A short name of this panel, not including any actual values */
    @SerialName("name")
    val name: kotlin.String? = null,

    @SerialName("title")
    val title: kotlin.String? = null,

    /* Used to indicate how the value of this item is measured, such as \"grade\" for Nutri-Score and Eco-Score or \"percentage\" for Salt */
    @SerialName("type")
    val type: TitleElement.Type? = null,

    /* The value for this panel where it corresponds to a A to E grade such as the Nutri-Score of the Eco-Score. */
    @SerialName("grade")
    val grade: TitleElement.Grade? = null,

    /* The numeric value of the panel, where the type is \"percentage\" */
    @SerialName("value")
    val `value`: Double? = null,

    @SerialName("icon_url")
    val iconUrl: kotlin.String? = null,

    @SerialName("icon_color_from_evaluation")
    val iconColorFromEvaluation: kotlin.String? = null,

    /* If set to \"small\", the icon should be displayed at a small size.  */
    @SerialName("icon_size")
    val iconSize: kotlin.String? = null

) {

    /**
     * Used to indicate how the value of this item is measured, such as \"grade\" for Nutri-Score and Eco-Score or \"percentage\" for Salt
     *
     * Values: grade,percentage
     */
    // @JsonClass(generateAdapter = false)
    enum class Type(val value: kotlin.String) {
        @SerialName("grade") grade("grade"),
        @SerialName("percentage") percentage("percentage");
    }
    /**
     * The value for this panel where it corresponds to a A to E grade such as the Nutri-Score of the Eco-Score.
     *
     * Values: a,b,c,d,e,unknown
     */
    // @JsonClass(generateAdapter = false)
    enum class Grade(val value: kotlin.String) {
        @SerialName("a") a("a"),
        @SerialName("b") b("b"),
        @SerialName("c") c("c"),
        @SerialName("d") d("d"),
        @SerialName("e") e("e"),
        @SerialName("unknown") unknown("unknown");
    }

}
