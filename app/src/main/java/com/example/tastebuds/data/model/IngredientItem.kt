package com.example.tastebuds.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class IngredientItem(
    val name: String,
    val quantity: String,
    val imageUrl: String
): Parcelable
