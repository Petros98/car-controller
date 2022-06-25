package com.example.carcontroller.model

data class Distance(
    val length: Int,
    val unit: String = "Mi"
) {
    override fun toString(): String =
        "$length $unit"
}
