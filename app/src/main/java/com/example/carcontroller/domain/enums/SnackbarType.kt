package com.example.carcontroller.domain.enums

import androidx.annotation.DrawableRes
import com.example.carcontroller.R

enum class SnackbarType(@DrawableRes val iconId: Int?) {
    SUCCESS(R.drawable.sym_check_fill),
    FAILURE(null);

    companion object {
        fun getByName(name: String?) = values().find { it.name.equals(name, true) }
    }
}