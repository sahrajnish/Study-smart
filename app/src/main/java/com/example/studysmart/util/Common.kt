package com.example.studysmart.util

import androidx.compose.ui.graphics.Color
import com.example.studysmart.ui.theme.Green
import com.example.studysmart.ui.theme.Orange
import com.example.studysmart.ui.theme.Red

enum class Priority(val title: String, val color: Color, val value: Int) {
    LOW("Low", Green, 0),
    MEDIUM("Medium", Orange, 1),
    HIGH("High", Red, 2);

    companion object {
        fun fromInt(value: Int) = values().firstOrNull() { it.value == value } ?: MEDIUM
    }
}