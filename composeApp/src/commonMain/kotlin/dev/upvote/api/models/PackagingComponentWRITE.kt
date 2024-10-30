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
 * Each packaging component has different properties to specify how many there are, its shape, material etc.  The shape, material and recycling properties will be mapped to one entry in the packaging_shapes, packaging_materials and packaging_recycling taxonomies.  For input, clients can either pass the id of a corresponding taxonomy entry (e.g. \"en:pizza-box\"), or a free text value prefixed with the language code of the text (e.g. \"en:Pizza box\", \"fr:boite à pizza\"). If the language code prefix is missing, the value of the \"lc\" field of the query will be used.  The resulting structure will contain the id of the canonical entry in the taxonomy if it good be matched, or the free text value prefixed with the language code otherwise.  For weights, the API is expecting a number with the number of grams. If a string is passed instead of a number, we will attempt to convert it to grams. The string may contain units (e.g. \"6.9 g\"), and use . or , as the decimal separator. Conversion may not work for all inputs. If a string was converted to a number, the API response will include a warning and specify the converted value.
 *
 * @param numberOfUnits Number of units of this packaging component contained in the product (e.g. 6 for a pack of 6 bottles)
 * @param shape The shape property is canonicalized using the packaging_shapes taxonomy.
 * @param material The material property is canonicalized using the packaging_materials taxonomy.
 * @param recycling The recycling property is canonicalized using the packaging_recycling taxonomy.
 * @param quantityPerUnit Quantity (weight or volume) of food product contained in the packaging component. (e.g. 75cl for a wine bottle)
 * @param weightSpecified 
 * @param weightMeasured 
 * @param brands A comma separated list of brands / product names for the packaging component (e.g. \"Tetra Pak\", Tetra Brik\"
 * @param labels A comma separated list of labels, canonicalized with the packaging_labels taxonomy (e.g. \"en:FSC, fr:Encre végétale\")
 */

@Serializable
data class PackagingComponentWRITE (

    /* Number of units of this packaging component contained in the product (e.g. 6 for a pack of 6 bottles) */
    @SerialName("number_of_units")
    val numberOfUnits: kotlin.Int? = null,

    /* The shape property is canonicalized using the packaging_shapes taxonomy. */
    @SerialName("shape")
    val shape: ByteArray? = null,

    /* The material property is canonicalized using the packaging_materials taxonomy. */
    @SerialName("material")
    val material: ByteArray? = null,

    /* The recycling property is canonicalized using the packaging_recycling taxonomy. */
    @SerialName("recycling")
    val recycling: ByteArray? = null,

    /* Quantity (weight or volume) of food product contained in the packaging component. (e.g. 75cl for a wine bottle) */
    @SerialName("quantity_per_unit")
    val quantityPerUnit: kotlin.String? = null,

    @SerialName("weight_specified")
    val weightSpecified: ByteArray? = null,

    @SerialName("weight_measured")
    val weightMeasured: ByteArray? = null,

    /* A comma separated list of brands / product names for the packaging component (e.g. \"Tetra Pak\", Tetra Brik\" */
    @SerialName("brands")
    val brands: kotlin.String? = null,

    /* A comma separated list of labels, canonicalized with the packaging_labels taxonomy (e.g. \"en:FSC, fr:Encre végétale\") */
    @SerialName("labels")
    val labels: kotlin.String? = null

) {


}
