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

import dev.upvote.api.models.PackagingComponentMaterial
import dev.upvote.api.models.PackagingComponentRecyclingInstruction
import dev.upvote.api.models.PackagingComponentShape

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Each packaging component has different properties to specify how many there are, its shape, material etc.  The shape, material and recycling properties are mapped to one entry in the packaging_shapes, packaging_materials and packaging_recycling taxonomies, and the value of the property is the canonical name of the taxonomy entry (e.g. en:bottle).  They may contain values that could not yet get matched to their respective taxonomy, in which case they will contain a free text value prefixed with the language code of this text value (e.g. \"fr:Bouteille sphérique\" might have been entered by a French user to indicate it is a spherical bottle).
 *
 * @param numberOfUnits umber of units of this packaging component contained in the product (e.g. 6 for a pack of 6 bottles)
 * @param shape 
 * @param material 
 * @param recycling 
 * @param quantityPerUnit Quantity (weight or volume) of food product contained in the packaging component. (e.g. 75cl for a wine bottle)
 * @param quantityPerUnitValue Value parsed from the quantity field.
 * @param quantityPerUnitUnit Unit parsed and normalized from the quantity field.
 * @param weightSpecified Weight (as specified by the manufacturer) of one unit of the empty packaging component (in grams). (e.g. for a 6 pack of 1.5l water bottles, it might be 30, the weight in grams of 1 empty water bottle without its cap which is a different packaging component).
 * @param weightMeasured Weight (as measured by one or more users) of one unit of the empty packaging component (in grams). (e.g. for a 6 pack of 1.5l water bottles, it might be 30, the weight in grams of 1 empty water bottle without its cap which is a different packaging component).
 * @param weightEstimated Weight (as estimated from similar products) of one unit of the empty packaging component (in grams). (e.g. for a 6 pack of 1.5l water bottles, it might be 30, the weight in grams of 1 empty water bottle without its cap which is a different packaging component).
 * @param weight Weight of one unit of the empty packaging component.
 * @param weightSourceId Indicates which field was used to populate the \"weight\" field. Either \"specified\", \"measured\", or \"estimated\"
 */

@Serializable
data class PackagingComponentREAD (

    /* umber of units of this packaging component contained in the product (e.g. 6 for a pack of 6 bottles) */
    @SerialName("number_of_units")
    val numberOfUnits: kotlin.Int? = null,

    @SerialName("shape")
    val shape: PackagingComponentShape? = null,

    @SerialName("material")
    val material: PackagingComponentMaterial? = null,

    @SerialName("recycling")
    val recycling: PackagingComponentRecyclingInstruction? = null,

    /* Quantity (weight or volume) of food product contained in the packaging component. (e.g. 75cl for a wine bottle) */
    @SerialName("quantity_per_unit")
    val quantityPerUnit: kotlin.String? = null,

    /* Value parsed from the quantity field. */
    @SerialName("quantity_per_unit_value")
    val quantityPerUnitValue: Double? = null,

    /* Unit parsed and normalized from the quantity field. */
    @SerialName("quantity_per_unit_unit")
    val quantityPerUnitUnit: kotlin.String? = null,

    /* Weight (as specified by the manufacturer) of one unit of the empty packaging component (in grams). (e.g. for a 6 pack of 1.5l water bottles, it might be 30, the weight in grams of 1 empty water bottle without its cap which is a different packaging component). */
    @SerialName("weight_specified")
    val weightSpecified: Double? = null,

    /* Weight (as measured by one or more users) of one unit of the empty packaging component (in grams). (e.g. for a 6 pack of 1.5l water bottles, it might be 30, the weight in grams of 1 empty water bottle without its cap which is a different packaging component). */
    @SerialName("weight_measured")
    val weightMeasured: Double? = null,

    /* Weight (as estimated from similar products) of one unit of the empty packaging component (in grams). (e.g. for a 6 pack of 1.5l water bottles, it might be 30, the weight in grams of 1 empty water bottle without its cap which is a different packaging component). */
    @SerialName("weight_estimated")
    val weightEstimated: Double? = null,

    /* Weight of one unit of the empty packaging component. */
    @SerialName("weight")
    val weight: Double? = null,

    /* Indicates which field was used to populate the \"weight\" field. Either \"specified\", \"measured\", or \"estimated\" */
    @SerialName("weight_source_id")
    val weightSourceId: kotlin.String? = null

) {


}

