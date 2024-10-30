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

import dev.upvote.api.models.PackagingComponentWRITE

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Model for creating or updating products using the v3 version of the product update API.
 *
 * @param packagings The packagings object is an array of individual packaging component objects.  The Packaging data document explains how packaging data is structured in Open Food Facts: https://openfoodfacts.github.io/openfoodfacts-server/dev/explain-packaging-data/
 * @param packagingsAdd The packagings object is an array of individual packaging component objects.  The Packaging data document explains how packaging data is structured in Open Food Facts: https://openfoodfacts.github.io/openfoodfacts-server/dev/explain-packaging-data/
 * @param packagingsComplete Indicate if the packagings array contains all the packaging parts of the product. This field can be set by users when they enter or verify packaging data. Possible values are 0 or 1.
 * @param lang 2 letter language code of the main language of the product (the most prominent on the packaging)
 * @param quantity 
 * @param servingSize 
 * @param categoriesTags An array of categories
 */

@Serializable
data class ProductUpdateAPIV3WRITE (

    /* The packagings object is an array of individual packaging component objects.  The Packaging data document explains how packaging data is structured in Open Food Facts: https://openfoodfacts.github.io/openfoodfacts-server/dev/explain-packaging-data/ */
    @SerialName("packagings")
    val packagings: kotlin.collections.List<PackagingComponentWRITE>? = null,

    /* The packagings object is an array of individual packaging component objects.  The Packaging data document explains how packaging data is structured in Open Food Facts: https://openfoodfacts.github.io/openfoodfacts-server/dev/explain-packaging-data/ */
    @SerialName("packagings_add")
    val packagingsAdd: kotlin.collections.List<PackagingComponentWRITE>? = null,

    /* Indicate if the packagings array contains all the packaging parts of the product. This field can be set by users when they enter or verify packaging data. Possible values are 0 or 1. */
    @SerialName("packagings_complete")
    val packagingsComplete: kotlin.Int? = null,

    /* 2 letter language code of the main language of the product (the most prominent on the packaging) */
    @SerialName("lang")
    val lang: kotlin.String? = null,

    @SerialName("quantity")
    val quantity: kotlin.String? = null,

    @SerialName("serving_size")
    val servingSize: kotlin.String? = null,

    /* An array of categories */
    @SerialName("categories_tags")
    val categoriesTags: kotlin.collections.List<kotlin.String>? = null

) {


}
