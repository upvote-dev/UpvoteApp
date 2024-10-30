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
 * Overall result  of the request (e.g. a product has been created)
 *
 * @param id Identifier of a response result entry
 * @param name Name of the response result entry in English.
 * @param lcName Name of the response result entry in the language specified in tags_lc, if supplied.
 */

@Serializable
data class ResponseStatusResult (

    /* Identifier of a response result entry */
    @SerialName("id")
    val id: kotlin.String? = null,

    /* Name of the response result entry in English. */
    @SerialName("name")
    val name: kotlin.String? = null,

    /* Name of the response result entry in the language specified in tags_lc, if supplied. */
    @SerialName("lc_name")
    val lcName: kotlin.String? = null

) {


}
