package com.oreakintobi.oreakintobi.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

typealias Filter = ArrayList<FilterElement>

@Parcelize
data class FilterElement(
    val id: String,
    val avatar: String,
    val fullName: String,
    val createdAt: String,
    val gender: String,
    val countries: List<String>,
    val colors: List<String>
) : Parcelable
