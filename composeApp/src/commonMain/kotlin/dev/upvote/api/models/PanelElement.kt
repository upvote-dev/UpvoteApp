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
 * Panels can include other panels as sub-panels using the panel_element.
 *
 * @param panelId The id of the panel to include. The id is the key of the panel in the panels object returned in the knowledge_panels field.
 */

@Serializable
data class PanelElement (

    /* The id of the panel to include. The id is the key of the panel in the panels object returned in the knowledge_panels field. */
    @SerialName("panel_id")
    val panelId: kotlin.String? = null

) {


}

