package com.example.compose.controller

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun customTextFieldColors(): TextFieldColors {
    return TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Color(android.graphics.Color.parseColor("#D81B60"))
    )
}

