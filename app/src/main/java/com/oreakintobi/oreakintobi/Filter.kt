// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json   = Json(JsonConfiguration.Stable)
// val filter = json.parse(Filter.serializer(), jsonString)

package com.oreakintobi.oreakintobi

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

typealias Filter = ArrayList<FilterElement>

@Serializable
data class FilterElement(
    val id: String? = null,
    val avatar: String? = null,
    val fullName: String? = null,
    val createdAt: String? = null,
    val gender: Gender? = null,
    val colors: List<Color>? = null,
    val countries: List<Country>? = null
)

@Serializable(with = Color.Companion::class)
enum class Color(val value: String) {
    Aquamarine("Aquamarine"),
    Blue("Blue"),
    Green("Green"),
    Maroon("Maroon"),
    Mauv("Mauv"),
    Orange("Orange"),
    Red("Red"),
    Teal("Teal"),
    Violet("Violet"),
    Yellow("Yellow");

    companion object : KSerializer<Color> {
        override val descriptor: SerialDescriptor
            get() {
                return StringDescriptor
            }

        override fun deserialize(decoder: Decoder): Color = when (decoder.decodeString()) {
            "Aquamarine" -> Aquamarine
            "Blue" -> Blue
            "Green" -> Green
            "Maroon" -> Maroon
            "Mauv" -> Mauv
            "Orange" -> Orange
            "Red" -> Red
            "Teal" -> Teal
            "Violet" -> Violet
            "Yellow" -> Yellow
            else -> throw IllegalArgumentException()
        }

        override fun serialize(encoder: Encoder, obj: Color) {
            return encoder.encodeString(obj.value)
        }
    }
}

@Serializable(with = Country.Companion::class)
enum class Country(val value: String) {
    China("China"),
    Colombia("Colombia"),
    Estonia("Estonia"),
    France("france"),
    Japan("Japan"),
    Mexico("Mexico"),
    SouthAfrica("South Africa");

    companion object : KSerializer<Country> {
        override val descriptor: SerialDescriptor
            get() {
                return StringDescriptor
            }

        override fun deserialize(decoder: Decoder): Country = when (decoder.decodeString()) {
            "China" -> China
            "Colombia" -> Colombia
            "Estonia" -> Estonia
            "france" -> France
            "Japan" -> Japan
            "Mexico" -> Mexico
            "South Africa" -> SouthAfrica
            else -> throw IllegalArgumentException()
        }

        override fun serialize(encoder: Encoder, obj: Country) {
            return encoder.encodeString(obj.value)
        }
    }
}

@Serializable(with = Gender.Companion::class)
enum class Gender(val value: String) {
    Female("female"),
    Male("male");

    companion object : KSerializer<Gender> {
        override val descriptor: SerialDescriptor
            get() {
                return StringDescriptor
            }

        override fun deserialize(decoder: Decoder): Gender = when (decoder.decodeString()) {
            "female" -> Female
            "male" -> Male
            else -> throw IllegalArgumentException()
        }

        override fun serialize(encoder: Encoder, obj: Gender) {
            return encoder.encodeString(obj.value)
        }
    }
}
