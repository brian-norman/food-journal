package com.example.foodjournal.model

import androidx.compose.Model
import androidx.ui.graphics.ImageAsset
import java.time.LocalDate

@Model
data class Entry(
    val id: Int,
    val restaurant: String,
    val date: LocalDate,
    val location: String?,
    val rating: Int,
    val review: String?,
    var photos: List<ImageAsset>,
    var dishes: List<Dish>
)
