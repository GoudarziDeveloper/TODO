package com.example.to_do_compose.utils

import androidx.compose.ui.graphics.Color
import com.example.to_do_compose.ui.theme.HighPriorityColor
import com.example.to_do_compose.ui.theme.LowPriorityColor
import com.example.to_do_compose.ui.theme.MediumPriorityColor
import com.example.to_do_compose.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    High(HighPriorityColor),
    Medium(MediumPriorityColor),
    Low(LowPriorityColor),
    None(NonePriorityColor)
}