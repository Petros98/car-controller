package com.example.carcontroller.model

import androidx.compose.ui.graphics.Color
import com.example.carcontroller.ui.theme.Black
import com.example.carcontroller.ui.theme.Brown
import com.example.carcontroller.ui.theme.Gray
import com.example.carcontroller.ui.theme.White

enum class ViewState(val backgroundColor: Color) {
    LOADING(White),
    ACTIVE(Brown),
    ENABLED(Black),
    DISABLED(Gray)
}