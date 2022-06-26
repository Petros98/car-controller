package com.example.carcontroller.domain.entity

data class Distance(
    val length: Int,
    val unit: String = "Mi"
) {
    override fun toString(): String =
        "$length $unit"
}
