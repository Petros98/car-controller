package com.example.carcontroller.model

import androidx.annotation.DrawableRes
import java.util.*

data class Vehicle(
    val id: UUID = UUID.randomUUID(),
    val model: String,
    val calculatedMileage: Distance,
    @DrawableRes val imageResId: Int,
    val doors: Doors,
    val engine: Engine
)