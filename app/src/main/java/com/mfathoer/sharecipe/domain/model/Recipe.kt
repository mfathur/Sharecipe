package com.mfathoer.sharecipe.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val summary: String,
    val sourceUrl: String
) : Parcelable
