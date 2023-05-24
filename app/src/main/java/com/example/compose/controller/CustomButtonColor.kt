package com.example.compose.controller

import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun customButtonColors(): ButtonColors {
    return ButtonDefaults.buttonColors(
        backgroundColor = Color(android.graphics.Color.parseColor("#D81B60")),
        contentColor = Color.White
    )
}
