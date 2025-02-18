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

import dev.upvote.api.models.TableElementColumnsInner

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Element to display a table.
 *
 * @param id An id for the table.
 * @param title Title of the column. 
 * @param rows 
 * @param columns 
 */

@Serializable
data class TableElement (

    /* An id for the table. */
    @SerialName("id")
    val id: kotlin.String? = null,

    /* Title of the column.  */
    @SerialName("title")
    val title: kotlin.String? = null,

    @SerialName("rows")
    val rows: kotlin.String? = null,

    @SerialName("columns")
    val columns: kotlin.collections.List<TableElementColumnsInner>? = null

) {


}

