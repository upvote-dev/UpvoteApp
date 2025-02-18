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

import dev.upvote.api.models.WarningOrErrorMessageField
import dev.upvote.api.models.WarningOrErrorMessageImpact
import dev.upvote.api.models.WarningOrErrorMessageMessage

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes a warning or error for a READ or WRITE request, which field triggered it, and what the impact was (e.g. the field was ignored).
 *
 * @param message 
 * @param `field` 
 * @param impact 
 */

@Serializable
data class WarningOrErrorMessage (

    @SerialName("message")
    val message: WarningOrErrorMessageMessage? = null,

    @SerialName("field")
    val `field`: WarningOrErrorMessageField? = null,

    @SerialName("impact")
    val impact: WarningOrErrorMessageImpact? = null

) {


}

