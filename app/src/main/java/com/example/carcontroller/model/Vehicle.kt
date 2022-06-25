package com.example.carcontroller.model

import androidx.annotation.DrawableRes

data class Vehicle(
    val model: String,
    val calculatedMileage: Distance,
    @DrawableRes val imageResId: Int,
    val doors: Doors,
    val engine: Engine
)